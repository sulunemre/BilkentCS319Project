package com.mygdx.game.stateControllers;

import com.badlogic.gdx.Gdx;

public class HelpStateController extends AbstractStateController {
    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();
        Gdx.gl.glClearColor(1, 0, 0, 0);
        Gdx.gl.glClearColor(0,0,1,1);
    }
}
