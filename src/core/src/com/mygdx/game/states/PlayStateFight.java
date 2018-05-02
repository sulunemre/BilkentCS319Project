package com.mygdx.game.states;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GameManager;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.*;
import com.mygdx.game.sprites.enemies.Enemy;
import com.mygdx.game.stateControllers.FightStateController;

public class PlayStateFight extends State {

    private BitmapFont scoreText, waveText, healthText, manaText;

    private double score;
    private Texture backgroundImage;
    private Vector2 backgroundPos1, backgroundPos2;
    private PlayerCharacter player;

    public PlayStateFight() {
        controller = new FightStateController();
        score = GameManager.getInstance().getScore();
        backgroundImage = new Texture("background1.png");
        controller.getCam().setToOrtho(false, MyGdxGame.WIDTH / 2, MyGdxGame.HEIGHT / 2);
        scoreText = new BitmapFont();
        waveText = new BitmapFont();
        healthText = new BitmapFont();
        manaText = new BitmapFont();

        backgroundPos1 = GameWorld.getInstance().getBackgroundPos1();
        backgroundPos2 = GameWorld.getInstance().getBackgroundPos2();
        player=GameWorld.getInstance().getPlayerCharacter();


       // for(int i =1; i < wave*5; i++){
         //   enemiesArray.add(new ((i * ( ROCK_SPACING + 52) + ROCK_SPACING/2)));
        //}

    }



    @Override
    public void render(SpriteBatch sb) {

            sb.setProjectionMatrix(controller.getCam().combined);
            sb.begin();
       //      sb.draw(backgroundImage, controller.getCam().position.x - (controller.getCam().viewportWidth /2 ), 0);
        sb.draw(backgroundImage, backgroundPos1.x, backgroundPos1.y);
        sb.draw(backgroundImage, backgroundPos2.x, backgroundPos2.y);
            for(GameElement ge : GameWorld.getInstance().getGameElementsArray()){
                sb.draw(ge.getElementTexture(), ge.getPosition().x, ge.getPosition().y);
            }

            scoreText.getData().setScale(0.5f);
            waveText.getData().setScale(0.5f);
            healthText.getData().setScale(0.5f);
            manaText.getData().setScale(0.5f);
            scoreText.draw(sb, "Score:" + score, player.getPosition().x - 120, 10);
            waveText.draw(sb, "Wave:" + ((FightStateController) controller).getWave(), player.getPosition().x - 70, 10);
            healthText.draw(sb, "Health:" + player.getHealth(), player.getPosition().x - 20, 10);
            manaText.draw(sb, "Mana:" + player.getMana(), player.getPosition().x + 20, 10);
            sb.end();

    }

    @Override
    public void dispose() {

    }
}
