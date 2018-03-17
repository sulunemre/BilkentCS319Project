package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class CreditsState extends State{

    SpriteBatch spriteBatch;
    BitmapFont font;

    String creators;
    public CreditsState(GameStateManager gam) {
        super(gam);

        spriteBatch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(1.2f);
        creators = "Emre Sülün\nAdahan Yalçinkaya\nMihri Nur Ceren\nBerk Erzin";
    }


    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch ab) {

        spriteBatch.begin();
        font.draw(spriteBatch, creators, Gdx.graphics.getWidth() /2 - 80, Gdx.graphics.getHeight() /2);
        spriteBatch.end();
    }

    @Override
    public void dispose() {

    }

    //Creates the mandatory skin of texts.
    //TODO: referans belirtilecek
//    private void createBasicSkin(){
//        //Create a font
//        BitmapFont font = new BitmapFont();
//        skin = new Skin();
//        skin.add("default", font);
//
//        //Create a texture
//        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth()/8,Gdx.graphics.getHeight()/20, Pixmap.Format.RGB888);
//        pixmap.setColor(Color.WHITE);
//        pixmap.fill();
//        skin.add("background",new Texture(pixmap));
//
////        //Create a button style
////        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
////        textButtonStyle.up = skin.newDrawable("background", Color.GRAY);
////        textButtonStyle.down = skin.newDrawable("background", Color.DARK_GRAY);
////        textButtonStyle.checked = skin.newDrawable("background", Color.DARK_GRAY);
////        textButtonStyle.over = skin.newDrawable("background", Color.LIGHT_GRAY);
////        textButtonStyle.font = skin.getFont("default");
////        skin.add("default", textButtonStyle);
//    }
}
