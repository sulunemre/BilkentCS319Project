package com.mygdx.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.GameManager;
import com.mygdx.game.stateControllers.AbstractStateController;

public abstract class State {

    protected Vector3 mouse;
    protected GameManager gameManager;
    protected GameStateManager gsm;
    protected AbstractStateController controller;
    protected SpriteBatch menuBatch;
    protected Texture background;
    //TODO: backgroundImage buraya taşınabilir

    protected State(){
        gameManager = GameManager.getInstance();
        gsm = GameStateManager.getInstance();
        mouse = new Vector3();
        menuBatch = new SpriteBatch();
        background = new Texture("menuBackground.png");
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
