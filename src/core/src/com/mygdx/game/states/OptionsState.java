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
//import com.badlogic.gdx.scenes.scene2d.ui.List<T>;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.lang.String;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.GameManager;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.stateControllers.OptionsStateController;

import java.util.List;
public class OptionsState extends State  {
    private TextButton volumeBtn;
    private Stage stage;
    private Skin skin;
    private Texture background;
    private GameManager gameManager;
    private  GameStateManager gsm;
    private int num=0;
    private TextButton back;

    protected OptionsState() {
        controller=new OptionsStateController();
        gameManager=GameManager.getInstance();
        gsm=GameStateManager.getInstance();
        stage =new Stage();
        Gdx.input.setInputProcessor(stage);
        createBasicSkin();
        background = new Texture("menuBackground.png");
        volumeBtn=new TextButton("VOLUME",skin);
        back=new TextButton("BACK",skin);
        stage.act();
        volumeBtn.setPosition(360 , 350);
        back.setPosition(360,300);
        stage.addActor(volumeBtn);
        stage.addActor(back);

        volumeBtn.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                num++;
                gameManager.setCount(num);
                if(gameManager.getCount()%2==1)
                {
                    gameManager.setFirstClicked(true);
                    gameManager.getCurrentMusic().pause();
                }
                else
                {
                    gameManager.setSecondClicked(true);
                    gameManager.getCurrentMusic().play();
                }

            }
        });
        back.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gsm.set(new MenuState());
            }
        });

    }





    @Override
    public void render(SpriteBatch ab) {
        ab.begin();
        ab.draw(background, 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        ab.end();
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
        skin.add("background",new Texture(pixmap));

        //Create a button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("background", Color.GRAY);
        textButtonStyle.down = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.over = skin.newDrawable("background", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);
    }
}
