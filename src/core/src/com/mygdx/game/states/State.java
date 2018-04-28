package com.mygdx.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.stateControllers.AbstractStateController;

public abstract class State {

    protected Vector3 mouse;
    protected GameStateManager gsm;
    protected AbstractStateController controller;

    protected State(){
        gsm = GameStateManager.getInstance();
        mouse = new Vector3();
    }
    public abstract void render(SpriteBatch ab);
    public abstract void dispose();

    public AbstractStateController getController() {
        return controller;
    }

    public void setController(AbstractStateController controller) {
        this.controller = controller;
    }
}
