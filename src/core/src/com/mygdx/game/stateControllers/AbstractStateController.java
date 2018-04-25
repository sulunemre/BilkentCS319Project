package com.mygdx.game.stateControllers;

public abstract class AbstractStateController {
    abstract void handleInput();
    abstract void update(float dt);
}
