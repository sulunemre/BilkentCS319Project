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
import com.mygdx.game.sprites.RockMoving;
import com.mygdx.game.sprites.powerups.PowerupFactory;
import com.mygdx.game.sprites.powerups.Powerups;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.MenuState;
import com.mygdx.game.states.PlayState;
import com.mygdx.game.states.PlayStateFight;

public class FlightStateController extends AbstractStateController{
    private GameWorld gameWorld;
    private GameManager gameManager;
    private GameStateManager gameStateManager;
    private PlayerCharacter playerCharacter;
    private Array<Rock> rocks;
    private Array<RockMoving> rocksMoving;


    private Texture backgroundImage;
    private Vector2 backgroundPos1, backgroundPos2;

    private static final int ROCK_SPACING = 125;
    private static final int ROCK_COUNT = 3;
    private static final int ROCKMOVING_COUNT = 3;
    private static final int BACKGROUND_Y_OFFSET = -30;
    private Sound effect;
    private Sound powerupSound;
    private Sound collisionSound;

    private double flightSpeed;

    public FlightStateController(){
        super();

        gameWorld = GameWorld.getInstance();
        gameManager = GameManager.getInstance();
        gameStateManager = GameStateManager.getInstance();
        effect=Gdx.audio.newSound(Gdx.files.internal("EnemyDeath.ogg"));
        powerupSound=Gdx.audio.newSound(Gdx.files.internal("Powerup.ogg"));
        collisionSound=Gdx.audio.newSound(Gdx.files.internal("Collision8-Bit.ogg"));
        playerCharacter = gameWorld.getPlayerCharacter();

        if(playerCharacter == null) {
            playerCharacter = new PlayerCharacter(50, 100);
            gameWorld.addPlayerCharacter(playerCharacter);
        }

        gameWorld.setPlayerCharacter(playerCharacter);
        gameWorld.addGameElements(playerCharacter);

        rocks = new Array<Rock>();
        rocksMoving = new Array<RockMoving>();
        for(int i =1; i < ROCK_COUNT; i++){
            Rock tempRock = new Rock(i * ( ROCK_SPACING + 52), 5);
            rocks.add(tempRock);
            gameWorld.addGameElements(tempRock);
        }
        for(int i =1; i < ROCKMOVING_COUNT; i++){
            RockMoving tempRockMoving = new RockMoving((i * ( ROCK_SPACING + 52) + ROCK_SPACING/2), 5);
            rocksMoving.add(tempRockMoving);
            gameWorld.addGameElements(tempRockMoving);
        }
        gameWorld.setRocks(rocks);
        gameWorld.setRocksMoving(rocksMoving);

        backgroundImage = new Texture("background1.png");
      //  backgroundPos1 = new Vector2(bgposx1, bgposy1);
      //  backgroundPos2 = new Vector2(bgposx2, bgposy2);
        backgroundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, BACKGROUND_Y_OFFSET);
        backgroundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + backgroundImage.getWidth(), BACKGROUND_Y_OFFSET);
        gameWorld.setBackgroundPos1(backgroundPos1);
        gameWorld.setBackgroundPos2(backgroundPos2);

        cam.setToOrtho(false, MyGdxGame.WIDTH / 2, MyGdxGame.HEIGHT / 2);

        if(gameManager.getCount()%2==0)
        {

            gameManager.getCurrentMusic().pause();
            gameManager.setMusic("flightStageMusic.mp3");
            //gameManager.getCurrentMusic().play();
        }



        flightSpeed = 1;
    }

    @Override
    public void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            playerCharacter.moveUp();

        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            playerCharacter.moveDown();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){ //TODO: test için yazıldı, sonra sil
            playerCharacter.moveLeft();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){ //TODO: test için yazıldı, sonra sil
            playerCharacter.moveRight();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.TAB)){

            gameStateManager.set(new PlayStateFight());
         //   for(int i = 0; i < game)
          //  gameWorld.removeGameElements(gameWorld.getEnemyArray());

        }
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            gameManager.getCurrentMusic().dispose();

            gameStateManager.set(new MenuState());
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



        Gdx.gl.glClearColor(1,0,0,1);

        for(Rock rock : rocks){

            if(cam.position.x - (cam.viewportWidth / 2)> rock.getPosition().x + rock.getElementTexture().getWidth()){
                rock.reposition(rock.getPosition().x + ((Rock.TUBE_WIDTH + ROCK_SPACING) * ROCK_COUNT));
            }
        }

        collision();

        for (RockMoving rock : rocksMoving ){
            if(rock.getDirection().y == 0)
                rock.setDirection(new Vector2(0,0));

            if ((rock.getPosition()).y > 250){
                rock.setDirection(new Vector2(0,0));
            }

            if ((rock.getPosition()).y < 15){
                rock.setDirection(new Vector2(0,0));
            }

            if(cam.position.x - (cam.viewportWidth / 2)> rock.getPosition().x + rock.getElementTexture().getWidth()){
                rock.reposition(rock.getPosition().x + ((Rock.TUBE_WIDTH + ROCK_SPACING) * ROCK_COUNT));
            }

            rock.move();
            rock.setRockBound();

            if(rock.collision(playerCharacter.getBounds()))
                gameStateManager.set(new PlayState());

        }

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
        //gameManager.setScore(gameManager.getScore() + playerCharacter.getAcceleration());
        // score = score / 10;
    }

    private void collision(){
        for(Rock rock: gameWorld.getRocks()) {
            if (rock.collision(playerCharacter.getBounds()))
            {
                collisionSound.play();
                gameManager.getCurrentMusic().pause();
                gameStateManager.set(new MenuState());
            }

        }
        for(Rock rock: gameWorld.getRocks()) {
            if (rock.collision(playerCharacter.getBounds()))
            {
                gameManager.getCurrentMusic().pause();
                gameStateManager.set(new MenuState());
            }

        }
        for(Powerups powerup : gameWorld.getPowerups()) {
            if (powerup.collision(playerCharacter.getBounds())) {
                powerupSound.play();
                powerup.activate(playerCharacter);
                gameWorld.removeGameElements(powerup);
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
