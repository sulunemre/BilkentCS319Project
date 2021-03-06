package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.stateControllers.HelpStateController;
//import com.badlogic.gdx.scenes.scene2d.ui.List<T>;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.lang.String;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.stateControllers.HelpStateController;

import java.util.List;
public class HelpState extends State  {

    private TextButton backBtn;
    private Stage stage;
    private Skin skin;
    private Texture background;

    public HelpState() {
        controller = new HelpStateController(this);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        createBasicSkin();
        background = new Texture("help.jpg");
        backBtn =new TextButton("BACK",skin);
        stage.act();
        backBtn.setPosition(340 , 90);
        stage.addActor(backBtn);
        backBtn.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameManager.getCurrentMusic().dispose();
                gsm.set(new MenuState());
            }
        });
    }

    @Override
    public void render(SpriteBatch ab) {
        menuBatch.begin();
        menuBatch.draw(background, 0, 0);
        menuBatch.end();
        stage.act();
        stage.draw();

    }

    @Override
    public void dispose() {

    }

    private void createBasicSkin(){
        //Create a font
        BitmapFont font = new BitmapFont();
        skin = new Skin();
        skin.add("default", font);

        //Create a texture
        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth()/8,Gdx.graphics.getHeight()/20, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        //skin.add("background",new Texture(pixmap));
        skin.add("help",new Texture(pixmap));

        //Create a button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("help", Color.GRAY);
        textButtonStyle.down = skin.newDrawable("help", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("help", Color.DARK_GRAY);
        textButtonStyle.over = skin.newDrawable("help", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);
    }
}
