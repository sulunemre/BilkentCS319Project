package com.mygdx.game.states;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameManager;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.stateControllers.FlightStateController;

public class PlayState extends State {
    private PlayerCharacter playerCharacter;
    private Texture backgroundImage;
    private Vector2 backgroundPos1, backgroundPos2;
    private GameWorld gameWorld;
    private BitmapFont healthText, manaText, scoreText, damageText;


    public PlayState() {
        controller = new FlightStateController(this);
        gameWorld = GameWorld.getInstance();
        scoreText = new BitmapFont();
        healthText = new BitmapFont();
        manaText = new BitmapFont();
        damageText = new BitmapFont();
        playerCharacter = gameWorld.getPlayerCharacter();
        backgroundPos1 = gameWorld.getBackgroundPos1();
        backgroundPos2 = gameWorld.getBackgroundPos2();
        backgroundImage = new Texture("background1.png");
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(controller.getCam().combined);
        sb.begin();
        sb.draw(backgroundImage, backgroundPos1.x, backgroundPos1.y);
        sb.draw(backgroundImage, backgroundPos2.x, backgroundPos2.y);

        for(GameElement ge: gameWorld.getGameElementsArray()){
            sb.draw(ge.getElementTexture(), ge.getPosition().x, ge.getPosition().y);
        }
        
        scoreText.draw(sb, "Score: " + (int) (gameManager.getScore()), playerCharacter.getPosition().x - 300, 20);
        healthText.draw(sb, "Health: " + playerCharacter.getHealth(), playerCharacter.getPosition().x - 150, 20);
        healthText.draw(sb, "Mana: " + playerCharacter.getMana(), playerCharacter.getPosition().x - 50, 20);
        damageText.draw(sb, "Damage: " + playerCharacter.getDamage(), playerCharacter.getPosition().x + 50, 20);
        sb.end();
    }

    @Override
    public void dispose() {

    }


}
