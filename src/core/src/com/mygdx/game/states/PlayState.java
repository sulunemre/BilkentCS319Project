package com.mygdx.game.states;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.PlayerCharacter;
import com.mygdx.game.sprites.Rock;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sprites.RockMoving;

import java.util.WeakHashMap;

public class PlayState extends State {
    private PlayerCharacter playerCharacter;
    private Texture backgroundImage;
    private Vector2 backgroundPos1, backgroundPos2;
    private static final int BACKGROUND_Y_OFFSET = -30;

    private Array<Rock> rocks;
    private Array<RockMoving> rocksM;

    private static final int ROCK_SPACING = 125;
    private static final int ROCK_COUNT = 3;
    private static final int ROCKMOVING_COUNT = 3;
    private Music music;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        playerCharacter = new PlayerCharacter(50,100);
        cam.setToOrtho(false, MyGdxGame.WIDTH / 2, MyGdxGame.HEIGHT / 2);
        backgroundImage = new Texture("background1.png");
        backgroundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, BACKGROUND_Y_OFFSET);
        backgroundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + backgroundImage.getWidth(), BACKGROUND_Y_OFFSET);
        rocks = new Array<Rock>();
        rocksM = new Array<RockMoving>();
        music = Gdx.audio.newMusic(Gdx.files.internal("flightStageMusic.mp3"));
        for(int i =1; i < ROCK_COUNT; i++){
            rocks.add(new Rock(i * ( ROCK_SPACING + 52)));
        }
        for(int i =1; i < ROCKMOVING_COUNT; i++){
            rocksM.add(new RockMoving((i * ( ROCK_SPACING + 52) + ROCK_SPACING/2)));
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            playerCharacter.moveup();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            playerCharacter.movedown();
        }


    }

    @Override
    public void update(float dt) {
        handleInput();
        updateBackground();
        playerCharacter.update(dt);
        cam.position.x = playerCharacter.getPosition().x + 80;
        Gdx.gl.glClearColor(1, 0, 0, 1);

        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();
        Gdx.gl.glClearColor(1,0,0,1);

        for(Rock rock : rocks){

            if(cam.position.x - (cam.viewportWidth / 2)> rock.getRock1Pos().x + rock.getRock1().getWidth()){
                rock.reposition(rock.getRock1Pos().x + ((Rock.TUBE_WIDTH + ROCK_SPACING) * ROCK_COUNT));
            }

            if(rock.collision(playerCharacter.getBounds()))
                gsm.set(new PlayState(gsm));
        }

        for (RockMoving rock : rocksM ){
            if(rock.getVelocity() == 0)
                rock.setVelocity(5);

            if ((rock.getRock1Pos()).y > 250){
                rock.setVelocity(-5);
            }

            if ((rock.getRock1Pos()).y < 15){
                rock.setVelocity(5);
            }

            if(cam.position.x - (cam.viewportWidth / 2)> rock.getRock1Pos().x + rock.getRock1().getWidth()){
                rock.reposition(rock.getRock1Pos().x + ((Rock.TUBE_WIDTH + ROCK_SPACING) * ROCK_COUNT));
            }

            rock.move();
            rock.setRockBound();

            if(rock.collision(playerCharacter.getBounds()))
                gsm.set(new PlayState(gsm));

        }

        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
       // sb.draw(backgroundImage, cam.position.x - (cam.viewportWidth /2 ), 0);
        sb.draw(backgroundImage, backgroundPos1.x, backgroundPos1.y);
        sb.draw(backgroundImage, backgroundPos2.x, backgroundPos2.y);
        sb.draw(playerCharacter.getPlayerCharacter(), playerCharacter.getPosition().x, playerCharacter.getPosition().y );
     //   sb.draw(rock.getRock1(), rock.getRock1Pos().x, rock.getRock1Pos().y);
        for(Rock rock: rocks){

            sb.draw(rock.getRock1(), rock.getRock1Pos().x, rock.getRock1Pos().y);
        }
        for(RockMoving rock: rocksM){

            sb.draw(rock.getRock1(), rock.getRock1Pos().x, rock.getRock1Pos().y);
        }
        sb.end();
    }

    @Override
    public void dispose() {

    }
    private void updateBackground(){
        if(cam.position.x - (cam.viewportWidth / 2) > backgroundPos1.x + backgroundImage.getWidth())
           backgroundPos1.add(backgroundImage.getWidth() * 2, 0);
        if(cam.position.x - (cam.viewportWidth / 2) > backgroundPos2.x + backgroundImage.getWidth())
            backgroundPos2.add(backgroundImage.getWidth() * 2, 0);




    }
}
