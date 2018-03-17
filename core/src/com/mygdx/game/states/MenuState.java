package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame;

public class MenuState extends State {

    private Texture background;
    private Texture playBtn;
    private Texture highScoresBtn;
    private Texture optionsBtn;
    private Texture creditsBtn;
    private Texture exitBtn;

    public MenuState(GameStateManager gam) {
        super(gam);
        background = new Texture("menuBackground.png");
        playBtn = new Texture("medieval-2.png");
        highScoresBtn = new Texture("medieval-2.png");
        optionsBtn = new Texture("medieval-2.png");
        creditsBtn = new Texture("medieval-2.png");
        exitBtn = new Texture("medieval-2.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
            dispose();

        }

    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        sb.draw(playBtn, (MyGdxGame.WIDTH / 2) - (playBtn.getWidth() / 2), MyGdxGame.HEIGHT / 2);
        sb.end();
    }

    @Override
    public void dispose() {
       background.dispose();
       playBtn.dispose();
    }
}
