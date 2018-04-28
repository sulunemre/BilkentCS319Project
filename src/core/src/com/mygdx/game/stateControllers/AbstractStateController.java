package com.mygdx.game.stateControllers;

import com.badlogic.gdx.graphics.OrthographicCamera;

public abstract class AbstractStateController {
    protected OrthographicCamera cam;
    public AbstractStateController() {
        cam = new OrthographicCamera();
    }

    public abstract void handleInput();
    public abstract void update(float dt);

    public OrthographicCamera getCam() {
        return cam;
    }

    public void setCam(OrthographicCamera cam) {
        this.cam = cam;
    }
}
