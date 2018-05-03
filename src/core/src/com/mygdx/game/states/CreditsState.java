package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.stateControllers.CreditsStateController;

public class CreditsState extends State{

    private Texture backgroundImage;


    public CreditsState() {
        backgroundImage = new Texture("creditsBackground.png");
        controller = new CreditsStateController(this);
    }



    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(backgroundImage, 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        sb.end();
    }

    @Override
    public void dispose() {
        backgroundImage.dispose();

    }
}
