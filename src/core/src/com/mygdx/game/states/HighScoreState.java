package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.stateControllers.HighScoreStateController;

public class HighScoreState extends State {
    private BitmapFont highScoreListText;

    public HighScoreState() {
        controller = new HighScoreStateController(this);
        highScoreListText = new BitmapFont();

    }

    @Override
    public void render(SpriteBatch ab) {
        menuBatch.begin();
        menuBatch.draw(background, 0, 0);
        highScoreListText.draw(menuBatch, ((HighScoreStateController) controller).getScoreListString(), 350, 500);
        menuBatch.end();
    }

    @Override
    public void dispose() {

    }
}
