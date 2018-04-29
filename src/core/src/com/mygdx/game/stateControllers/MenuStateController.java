package com.mygdx.game.stateControllers;

import com.badlogic.gdx.Gdx;

public class MenuStateController extends AbstractStateController {

    public MenuStateController() {
        gameManager.setMusic("stormwind.mp3");
        gameManager.getCurrentMusic().setLooping(true);
        gameManager.getCurrentMusic().play();
    }

    @Override
    public void handleInput() {}

    @Override
    public void update(float dt) {
        handleInput();
        Gdx.gl.glClearColor(1, 0, 0, 1);



        Gdx.gl.glClearColor(1,0,0,1);
    }
}
