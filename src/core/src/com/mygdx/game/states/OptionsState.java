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
import com.mygdx.game.MyGdxGame;
import java.util.List;
public class OptionsState extends State  {
    private TextButton volumeBtn;
    private Stage stage;
    private Skin skin;
    private Texture background;
    private Music music;
    private TextButton back;

    protected OptionsState(GameStateManager gsm) {
        super(gsm);
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
        music = Gdx.audio.newMusic(Gdx.files.internal("stormwind.mp3"));
        volumeBtn.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                music.pause();
                //gsm.set(new MenuState(gsm));
            }
        });
        back.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                music.pause();
                gsm.set(new MenuState(gsm));
            }
        });

    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();
        Gdx.gl.glClearColor(1, 0, 0, 0);
        if(volumeBtn.isPressed()==true)
            music.pause();
        else
        {
            music.setLooping(true);
            music.pause();
            music.setVolume(0.1f);
            music.play();
        }

        Gdx.gl.glClearColor(0,0,1,1);

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
