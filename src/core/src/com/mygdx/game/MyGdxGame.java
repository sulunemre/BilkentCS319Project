package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.MenuState;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.states.PlayState;

public class MyGdxGame extends ApplicationAdapter {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;

	public static final String TITLE = "Fight or Flight";

	private GameStateManager gsm;
	private SpriteBatch batch;
	Texture img;
	

	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = GameStateManager.getInstance();
		img = new Texture("menuBackground.png");

		gsm.push(new PlayState());
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);

	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
