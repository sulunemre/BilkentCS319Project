package com.mygdx.game.states;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;
public class GameStateManager {
    private static GameStateManager gameStateManager = new GameStateManager();

    private Stack<State> states = new Stack<State>();;

    public static GameStateManager getInstance(){
        return gameStateManager;
    }

    public GameStateManager(){
    }

    public void push(State state){

        states.push(state);
    }
    public void pop(){

        states.pop();
    }
    public void set(State state){
        states.pop();
        states.push(state);


    }
    public void update(float dt){
        states.peek().update(dt);
    }
    public void render(SpriteBatch ab){
        states.peek().render(ab);
    }
}
