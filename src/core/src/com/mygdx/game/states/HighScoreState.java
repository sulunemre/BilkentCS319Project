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
        ab.begin();
        highScoreListText.draw(ab, ((HighScoreStateController) controller).getScoreListString(), 150, 150);
        ab.end();
    }

    @Override
    public void dispose() {

    }
}
