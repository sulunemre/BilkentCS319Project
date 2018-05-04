package com.mygdx.game.stateControllers;

import com.mygdx.game.Score;
import com.mygdx.game.states.State;

import java.io.IOException;
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

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    public List<Score> getScoreList() {
        return scoreList;
    }

    public void updateScoreList() throws IOException, ClassNotFoundException {
        scoreList = gameManager.getHighScoreList();
        scoreListString = "";
        for(int i=0; i< scoreList.size(); i++){
            scoreListString = scoreListString + (i+1) + ") " + scoreList.get(i) + "\n";
        }
    }

    public String getScoreListString() {
        return scoreListString;
    }
}
