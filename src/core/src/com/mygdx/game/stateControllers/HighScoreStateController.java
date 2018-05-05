package com.mygdx.game.stateControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.Score;
import com.mygdx.game.states.MenuState;
import com.mygdx.game.states.State;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class HighScoreStateController extends AbstractStateController {
    private List<Score> scoreList;
    private String scoreListString;

    public HighScoreStateController(State state){
        super(state);
        try {
            updateScoreList();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            controlledState.dispose();
            gameManager.getCurrentMusic().pause();
            gameStateManager.set(new MenuState());
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        if (gameManager.getCount() % 2 == 1) {
            gameManager.getCurrentMusic().pause();
        }
        else {
            gameManager.getCurrentMusic().play();
        }

    }

    public List<Score> getScoreList() {
        return scoreList;
    }

    private void updateScoreList() throws IOException, ClassNotFoundException {
        scoreList = gameManager.getHighScoreList();
        Collections.sort(scoreList, Collections.<Score>reverseOrder()); // Sort high score list
        scoreListString = "";
        for(int i=0; i< scoreList.size(); i++){
            scoreListString = scoreListString + (i+1) + ") " + scoreList.get(i) + "\n";
        }
    }

    public String getScoreListString() {
        return scoreListString;
    }
}
