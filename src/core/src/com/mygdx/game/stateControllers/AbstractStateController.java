package com.mygdx.game.stateControllers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.GameManager;
import com.mygdx.game.states.GameStateManager;

public abstract class AbstractStateController {
    protected OrthographicCamera cam;
    protected OrthographicCamera cam2;
    protected GameStateManager gameStateManager;
    protected GameManager gameManager;



    public AbstractStateController() {
        cam = new OrthographicCamera();
        cam2=new OrthographicCamera();
        gameStateManager = GameStateManager.getInstance();
        gameManager = GameManager.getInstance();
    }

    public abstract void handleInput();
    public abstract void update(float dt);

    public OrthographicCamera getCam() {
        return cam;
    }

    public void setCam(OrthographicCamera cam) {
        this.cam = cam;
    }

    public OrthographicCamera getCam2() {
        return cam2;
    }

    public void setCam2(OrthographicCamera cam2) {
        this.cam2 = cam2;
    }
}