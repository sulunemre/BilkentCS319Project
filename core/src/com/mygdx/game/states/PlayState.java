package com.mygdx.game.states;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.PlayerCharacter;

import java.util.WeakHashMap;

public class PlayState extends State {
    private PlayerCharacter playerCharacter;
    public PlayState(GameStateManager gsm) {
        super(gsm);
        playerCharacter = new PlayerCharacter(50,100);
        cam.setToOrtho(false, MyGdxGame.WIDTH / 2, MyGdxGame.HEIGHT / 2);
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
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(playerCharacter.getPlayerCharacter(), playerCharacter.getPosition().x, playerCharacter.getPosition().y );
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
