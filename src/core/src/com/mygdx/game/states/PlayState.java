package com.mygdx.game.states;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.PlayerCharacter;
import com.mygdx.game.sprites.Rock;
import com.badlogic.gdx.utils.Array;

import java.util.WeakHashMap;

public class PlayState extends State {
    private PlayerCharacter playerCharacter;
    private Texture backgroundImage;


    private Array<Rock> rocks;

    private static final int ROCK_SPACING = 125;
    private static final int ROCK_COUNT = 4;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        playerCharacter = new PlayerCharacter(50,100);
        cam.setToOrtho(false, MyGdxGame.WIDTH / 2, MyGdxGame.HEIGHT / 2);
        backgroundImage = new Texture("background1.png");

        rocks = new Array<Rock>();

        for(int i =1; i < ROCK_COUNT; i++){

            rocks.add(new Rock(i * (ROCK_SPACING + Rock.TUBE_WIDTH)));
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
        playerCharacter.update(dt);
        cam.position.x = playerCharacter.getPosition().x + 80;

        for(Rock rock : rocks){

            if(cam.position.x - (cam.viewportWidth / 2)> rock.getRock1Pos().x + rock.getRock1().getWidth()){
                rock.reposition(rock.getRock1Pos().x + ((Rock.TUBE_WIDTH + ROCK_SPACING) * ROCK_COUNT));
            }

            if(rock.collision(playerCharacter.getBounds()))
                gsm.set(new PlayState(gsm));
        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(backgroundImage, cam.position.x - (cam.viewportWidth /2 ), 0);
        sb.draw(playerCharacter.getPlayerCharacter(), playerCharacter.getPosition().x, playerCharacter.getPosition().y );
     //   sb.draw(rock.getRock1(), rock.getRock1Pos().x, rock.getRock1Pos().y);
        for(Rock rock: rocks){

            sb.draw(rock.getRock1(), rock.getRock1Pos().x, rock.getRock1Pos().y);
        }
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
