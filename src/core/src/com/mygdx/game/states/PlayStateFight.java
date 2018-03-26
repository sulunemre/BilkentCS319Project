package com.mygdx.game.states;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.PlayerCharacter;
import com.mygdx.game.sprites.Rock;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sprites.RockMoving;
import com.mygdx.game.sprites.enemies.Enemy;

import java.util.WeakHashMap;

public class PlayStateFight extends State {
    private PlayerCharacter playerCharacter;
    private Texture backgroundImage;
    private Vector2 backgroundPos1, backgroundPos2;
    private static final int BACKGROUND_Y_OFFSET = -30;
    public static boolean fightOrFlight;
    private Array<Rock> rocks;
    private Array<RockMoving> rocksM;
    private Array<Enemy> enemiesArray;
    private BitmapFont scoreText;
    private double score;
    private int wave;
    private static final int ROCK_SPACING = 125;
    private static final int ROCK_COUNT = 4;
    private static final int ROCKMOVING_COUNT = 3;
    private Music music;

    public PlayStateFight(GameStateManager gsm, float xLoc, float yLoc, float bgposx1, float  bgposy1, float bgposx2, float bgposy2, double prevScore) {
        super(gsm);
        playerCharacter = new PlayerCharacter(xLoc,yLoc, true);
       // playerCharacter.changeMode();
        score = prevScore;
        wave = 1;
        cam.setToOrtho(false, MyGdxGame.WIDTH / 2, MyGdxGame.HEIGHT / 2);
        scoreText = new BitmapFont();
        backgroundImage = new Texture("background1.png");
        backgroundPos1 = new Vector2(bgposx1, bgposy1);
        backgroundPos2 = new Vector2(bgposx2, bgposy2);
        rocks = new Array<Rock>();
        rocksM = new Array<RockMoving>();
        enemiesArray = new Array<Enemy>();
        music = Gdx.audio.newMusic(Gdx.files.internal("flightStageMusic.mp3"));
        fightOrFlight = false;  // 0 for flight 1 for fight
        for(int i =1; i < ROCK_COUNT; i++){

            rocks.add(new Rock(i * (ROCK_SPACING + Rock.TUBE_WIDTH)));
        }
        for(int i =1; i < ROCKMOVING_COUNT; i++){
            rocksM.add(new RockMoving((i * ( ROCK_SPACING + 52) + ROCK_SPACING/2)));
        }
       // for(int i =1; i < wave*5; i++){
         //   enemiesArray.add(new ((i * ( ROCK_SPACING + 52) + ROCK_SPACING/2)));
        //}

    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            playerCharacter.moveup();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            playerCharacter.movedown();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            playerCharacter.moveleft();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            playerCharacter.moveright();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.Q)){
            music.pause();
            gsm.set(new PlayState(gsm, playerCharacter.getPosition().x, playerCharacter.getPosition().y, backgroundPos1.x , backgroundPos1.y, backgroundPos2.x, backgroundPos2.y, score));

            //   gsm.set(new PlayState(gsm, playerCharacter.getPosition().x, playerCharacter.getPosition().y, backgroundPos1.x , backgroundPos1.y, backgroundPos2.x, backgroundPos2.y));
        }
    }

    @Override
    public void update(float dt) {


            handleInput();
            updateBackground();
            playerCharacter.update(dt);
            cam.position.x = playerCharacter.getPosition().x + 80;
            Gdx.gl.glClearColor(1, 0, 0, 1);
            System.out.println(score);
            music.setLooping(true);
            music.setVolume(0.1f);
            music.play();
            Gdx.gl.glClearColor(1, 0, 0, 1);
            for (RockMoving rock : rocksM ){
            if(rock.getVelocity().y == 0)
                rock.setVelocity(new Vector2(0,5));

            if ((rock.getPosition()).y > 250){
                rock.setVelocity(new Vector2(0,-5));
            }

            if ((rock.getPosition()).y < 15){
                rock.setVelocity(new Vector2(0,5));
            }

            if(cam.position.x - (cam.viewportWidth / 2)> rock.getPosition().x + rock.getElementTexture().getWidth()){
                rock.reposition(rock.getPosition().x + ((Rock.TUBE_WIDTH + ROCK_SPACING) * ROCK_COUNT));
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
            sb.draw(playerCharacter.getElementTexture(), playerCharacter.getPosition().x, playerCharacter.getPosition().y);
            //   sb.draw(rock.getRock1(), rock.getRock1Pos().x, rock.getRock1Pos().y);
            for (Rock rock : rocks) {

                sb.draw(rock.getElementTexture(), rock.getPosition().x, rock.getPosition().y);
            }
            for(RockMoving rock: rocksM){

            sb.draw(rock.getElementTexture(), rock.getPosition().x, rock.getPosition().y);
            }
            scoreText.getData().setScale(0.5f);
            scoreText.draw(sb, "Score:" + score, playerCharacter.getPosition().x - 80, 20);
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
    public static void changeMode(){
        if(fightOrFlight == false){
            fightOrFlight = true;
        }
        else{
            fightOrFlight = false;
        }

    }
}
