package com.mygdx.game.stateControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GameManager;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.GameWorld;
import com.mygdx.game.sprites.PlayerCharacter;
import com.mygdx.game.sprites.Rock;
import com.mygdx.game.sprites.powerups.PowerupFactory;
import com.mygdx.game.sprites.powerups.Powerups;
import com.mygdx.game.states.*;
import java.util.Random;

public class FlightStateController extends AbstractStateController{
    private GameWorld gameWorld;
    private GameManager gameManager;
    private GameStateManager gameStateManager;
    private PlayerCharacter playerCharacter;
    private Array<Rock> rocks;


    private Texture backgroundImage;
    private Vector2 backgroundPos1, backgroundPos2;

    private static final int BACKGROUND_Y_OFFSET = -30;
    private int rocktrigger = 450;

    private Random rand;

    private Sound effect;
    private Sound powerupSound;
    private Sound collisionSound;

    private double flightSpeed;

    public FlightStateController(State state){
        super(state);

        gameWorld = GameWorld.getInstance();
        gameManager = GameManager.getInstance();
        gameStateManager = GameStateManager.getInstance();
        effect=Gdx.audio.newSound(Gdx.files.internal("EnemyDeath.ogg"));
        powerupSound=Gdx.audio.newSound(Gdx.files.internal("Powerup.ogg"));
        collisionSound=Gdx.audio.newSound(Gdx.files.internal("Collision8-Bit.ogg"));
        playerCharacter = gameWorld.getPlayerCharacter();
        rand = new Random();

        if(playerCharacter == null) {
            playerCharacter = new PlayerCharacter(50, 100);
            gameWorld.addPlayerCharacter(playerCharacter);
        }

        gameWorld.setPlayerCharacter(playerCharacter);
        gameWorld.addGameElements(playerCharacter);

        rocks = new Array<Rock>();

        gameWorld.setRocks(rocks);

        backgroundImage = new Texture("background1.png");
      //  backgroundPos1 = new Vector2(bgposx1, bgposy1);
      //  backgroundPos2 = new Vector2(bgposx2, bgposy2);
        backgroundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, BACKGROUND_Y_OFFSET);
        backgroundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + backgroundImage.getWidth(), BACKGROUND_Y_OFFSET);
        gameWorld.setBackgroundPos1(backgroundPos1);
        gameWorld.setBackgroundPos2(backgroundPos2);

        cam.setToOrtho(false, MyGdxGame.WIDTH  , MyGdxGame.HEIGHT  );

        if(gameManager.getCount()%2==0)
        {

            gameManager.getCurrentMusic().pause();
            gameManager.setMusic("flightStageMusic.mp3");
            //gameManager.getCurrentMusic().play();
        }



        flightSpeed = 1;
        playerCharacter.setDirection(1,0);
    }

    @Override
    public void handleInput() {
        Input input = Gdx.input;
        if(input.isKeyPressed(Input.Keys.W)){
            playerCharacter.moveUp();
        }
        if(input.isKeyPressed(Input.Keys.S)){
            playerCharacter.moveDown();
        }
        if(input.isKeyPressed(Input.Keys.A)){ //TODO: test için yazıldı, sonra sil
            playerCharacter.moveLeft();
        }
        if(input.isKeyPressed(Input.Keys.D)){ //TODO: test için yazıldı, sonra sil
            playerCharacter.moveRight();
        }
        if(input.isKeyPressed(Input.Keys.TAB)){
            gameStateManager.set(new PlayStateFight());
        }
        if(input.isKeyPressed(Input.Keys.ESCAPE)){
            gameManager.getCurrentMusic().dispose();
            gameStateManager.set(new MenuState());
        }
        if(input.isKeyJustPressed(Input.Keys.F5)){
            //TODO: implement save game
        }
    }

    @Override
    public void update(float dt) {
        System.out.println(flightSpeed);
        handleInput();
        updateBackground();
        gameWorld.updateAll(dt);
        cam.position.x = gameWorld.getPlayerCharacter().getPosition().x + 80;
        //cam.position.x=cam2.position.x;
        Gdx.gl.glClearColor(1, 0, 0, 1);

        flightSpeed++;
        playerCharacter.setSpeed((flightSpeed / 20));
        increaseScore();
        if(gameManager.getCount()%2==0 )
        {
            gameManager.getCurrentMusic().setLooping(true);
            gameManager.getCurrentMusic().play();
        }

        if (cam.position.x >= rocktrigger){
            int type = rand.nextInt(3);
            Rock rock;

            if ( type == 2 )
                rock = new Rock (cam.position.x + cam.viewportWidth + 20, 5, 10);
            else
                rock = new Rock (cam.position.x + cam.viewportWidth + 20, 5);
            rocks.add(rock);
            gameWorld.addGameElements(rock);
            rocktrigger =  rocktrigger + 100 + rand.nextInt( 150);
        }


        for ( Rock rock : rocks){
            if ( rock.getPositionx() <= cam.position.x - cam.viewportWidth){
                gameWorld.removeGameElements(rock);
                rocks.removeValue(rock,false);
            }
        }

        Gdx.gl.glClearColor(1,0,0,1);

        collision();

        cam.update();

        // Powerup cration logic
        if(flightSpeed < 500 && flightSpeed % 100 == 0)
            createPowerup(0 );
    }

    private void updateBackground(){
        if(cam.position.x - (cam.viewportWidth / 2) > backgroundPos1.x + backgroundImage.getWidth())
            backgroundPos1.add(backgroundImage.getWidth() * 2, 0);
        if(cam.position.x - (cam.viewportWidth / 2) > backgroundPos2.x + backgroundImage.getWidth())
            backgroundPos2.add(backgroundImage.getWidth() * 2, 0);

    }

    private void increaseScore(){
        gameManager.increseScore((int) flightSpeed);
    }

    private void collision(){
        /*for(Rock rock: gameWorld.getRocks()) {
            for (PlayerCharacter player: gameWorld.getPlayerCharacterArray()) {
                if (rock.collision(player.getBounds())) {
                    collisionSound.play();
                    gameManager.getCurrentMusic().pause();
                    gameWorld.removeGameElements(player);
                }
            }

        }*/
        for(Rock rock: gameWorld.getRocks()) {
            if (rock.collision(playerCharacter.getBounds())) {
                collisionSound.play();
                gameManager.getCurrentMusic().pause();
                gameManager.gameOver();
            }

        }
        for(Powerups powerup : gameWorld.getPowerups()) {
            if (powerup.collision(playerCharacter.getBounds())) {
                powerupSound.play();
                playerCharacter.collectPowerup(powerup);
                gameWorld.removeGameElements(powerup);
                gameWorld.getPowerups().removeValue(powerup, true);
            }
        }
    }

    /**
     * @param level 0 for low level, 1 for medium, 2 for high level powerups
     */
    private void createPowerup(int level){
        int yLocation = (int) (Math.random()*260);
        int xLocation = (int) (Math.random()*300 + playerCharacter.getPosition().x + 50);
        PowerupFactory powerupFactory = new PowerupFactory();
        Powerups powerup;

        if(level == 0){
            powerup = powerupFactory.getLowLevelPowerup();
        }
        else if(level == 1){
            powerup = powerupFactory.getMediumLevelPowerup();
        }
        else if(level == 2){
            powerup = powerupFactory.getHighLevelPowerup();
        }
        else{
            throw new IllegalArgumentException();
        }

        powerup.setPosition(new Vector2(xLocation, yLocation));
        gameWorld.addGameElements(powerup);
        gameWorld.getPowerups().add(powerup);
    }


}
