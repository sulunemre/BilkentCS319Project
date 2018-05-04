package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGdxGame;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.stateControllers.MenuStateController;
import javax.swing.*;
import java.awt.*;

public class MenuState extends State {

    private Texture background;

    private TextButton playBtn;
    private TextButton highScoresBtn;
    private TextButton optionsBtn;
    private TextButton creditsBtn;
    private TextButton helpBtn;
    private TextButton exitBtn;

    private Skin skin;
    private Stage stage;
    public MenuState() {
        controller = new MenuStateController(this);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        createBasicSkin();

        background = new Texture("menuBackground.png");

        playBtn = new TextButton("Play", skin);
        highScoresBtn = new TextButton("High scores", skin);
        optionsBtn = new TextButton("Options", skin);
        creditsBtn = new TextButton("Credits", skin);
        exitBtn = new TextButton("Exit", skin);
        helpBtn = new TextButton("Help",skin);

        //Set positions of buttons
        playBtn.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/16 , Gdx.graphics.getHeight()/2 + 20);
        highScoresBtn.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/16 , Gdx.graphics.getHeight()/2 - 25);
        optionsBtn.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/16 , Gdx.graphics.getHeight()/2 - 70);
        creditsBtn.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/16 , Gdx.graphics.getHeight()/2 - 115);
        helpBtn.setPosition(Gdx.graphics.getWidth()/2-Gdx.graphics.getWidth()/16, Gdx.graphics.getHeight()/2-155);
        exitBtn.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/16 , Gdx.graphics.getHeight()/2 - 190);

        //Add buttons to stage
        stage.addActor(playBtn);
        stage.addActor(highScoresBtn);
        stage.addActor(optionsBtn);
        stage.addActor(creditsBtn);
        stage.addActor(exitBtn);
        stage.addActor(helpBtn);


        playBtn.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                JFrame frame = new JFrame();
                gameManager.setPlayerName(login(frame));
                gameManager.setUpNewGame();
                gsm.set(new PlayState());
                dispose();
            }

        });

        highScoresBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gsm.set(new HighScoreState());
                dispose();
            }
        });

        creditsBtn.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gsm.set(new CreditsState());
                dispose();
            }
        });
        helpBtn.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gsm.set(new HelpState());
                dispose();
            }
        });
        optionsBtn.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gsm.set(new OptionsState());
                dispose();
            }
        });
        exitBtn.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });



    }



    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        sb.end();

        stage.act();
        stage.draw();

    }

    @Override
    public void dispose() {
       background.dispose();
       stage.dispose();
    }


    //Creates the mandatory skin of buttons.
    //TODO: referans belirtilecek
    private void createBasicSkin(){
        //Create a font
        BitmapFont font = new BitmapFont();
        skin = new Skin();
        skin.add("default", font);

        //Create a texture
        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth()/8,Gdx.graphics.getHeight()/20, Pixmap.Format.RGB888);
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

    public String login(JFrame frame) {
        String input = new String();

        JPanel panel = new JPanel(new BorderLayout(5, 5));

        JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
        label.add(new JLabel("Player Name", SwingConstants.RIGHT));
        panel.add(label, BorderLayout.WEST);

        JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
        JTextField username = new JTextField();
        controls.add(username);
        panel.add(controls, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(frame, panel, "", JOptionPane.QUESTION_MESSAGE);

        input = username.getText();
        return input;
    }
}

