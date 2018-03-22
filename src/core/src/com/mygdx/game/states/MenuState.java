package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
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

public class MenuState extends State {

    private Texture background;

    private TextButton playBtn;
    private TextButton highScoresBtn;
    private TextButton optionsBtn;
    private TextButton creditsBtn;
    private TextButton exitBtn;

    private Skin skin;
    private Stage stage;
    private Music music;
    public MenuState(GameStateManager gam) {
        super(gam);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        createBasicSkin();

        background = new Texture("menuBackground.png");

        playBtn = new TextButton("Play", skin);
        highScoresBtn = new TextButton("High scores", skin);
        optionsBtn = new TextButton("Options", skin);
        creditsBtn = new TextButton("Credits", skin);
        exitBtn = new TextButton("Exit", skin);

        //Set positions of buttons
        playBtn.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/16 , Gdx.graphics.getHeight()/2 + 20);
        highScoresBtn.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/16 , Gdx.graphics.getHeight()/2 - 25);
        optionsBtn.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/16 , Gdx.graphics.getHeight()/2 - 70);
        creditsBtn.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/16 , Gdx.graphics.getHeight()/2 - 115);
        exitBtn.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/16 , Gdx.graphics.getHeight()/2 - 160);

        //Add buttons to stage
        stage.addActor(playBtn);
        stage.addActor(highScoresBtn);
        stage.addActor(optionsBtn);
        stage.addActor(creditsBtn);
        stage.addActor(exitBtn);
        music = Gdx.audio.newMusic(Gdx.files.internal("stormwind.mp3"));

        playBtn.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                music.pause();
                gsm.set(new PlayState(gsm));
            }
        });

        creditsBtn.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                music.pause();
                gsm.set(new CreditsState(gsm));
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
    public void handleInput() {}

    @Override
    public void update(float dt) {
        handleInput();
        Gdx.gl.glClearColor(1, 0, 0, 1);

        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();
        Gdx.gl.glClearColor(1,0,0,1);
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
}
