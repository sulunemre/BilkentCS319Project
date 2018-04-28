package com.mygdx.game.stateControllers;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GameManager;
import com.mygdx.game.sprites.GameWorld;
import com.mygdx.game.sprites.PlayerCharacter;
import com.mygdx.game.sprites.Rock;
import com.mygdx.game.sprites.RockMoving;
import com.mygdx.game.sprites.enemies.Enemy;
import com.mygdx.game.states.PlayState;


public class FightStateController extends AbstractStateController {
    private GameWorld gameWorld;
    private GameManager gameManager;
    private PlayerCharacter playerCharacter;
    private Array<Enemy> enemyArray;
    private int wave;
    private boolean waveCleared;

    private Array<Rock> rocks;
    private Array<RockMoving> rocksM;
    private Vector2 backgroundPos1, backgroundPos2;
//    private static final int BACKGROUND_Y_OFFSET = -30;
 //   private Texture backgroundImage;
    private static final int ROCK_SPACING = 125;
    private static final int ROCK_COUNT = 3;
    private static final int ROCKMOVING_COUNT = 3;

    public FightStateController() {
        gameWorld = GameWorld.getInstance();
        gameManager = GameManager.getInstance();
        playerCharacter = gameWorld.getPlayerCharacter();
        enemyArray = gameWorld.getEnemyArray();

        wave = 1;
        waveCleared = true;

        rocks = new Array<Rock>();
        rocksM = new Array<RockMoving>();
        enemyArray = new Array<Enemy>();
        for(int i =1; i < ROCK_COUNT; i++){

            rocks.add(new Rock(i * (ROCK_SPACING + Rock.TUBE_WIDTH), 5));
        }
//        backgroundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, BACKGROUND_Y_OFFSET);
//        backgroundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + backgroundImage.getWidth(), BACKGROUND_Y_OFFSET);
//        gameWorld.setBackgroundPos1(backgroundPos1);
//        gameWorld.setBackgroundPos2(backgroundPos2);
    }

    @Override
    public void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            playerCharacter.moveUp();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            playerCharacter.moveDown();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            playerCharacter.moveLeft();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            playerCharacter.moveRight();
        }
//        if(Gdx.input.isKeyPressed(Input.Keys.Q)){
//            music.pause();
//            // gsm.set(new PlayState(playerCharacter.getPosition().x, playerCharacter.getPosition().y,  backgroundPos1.x , backgroundPos1.y, backgroundPos2.x, backgroundPos2.y, score));


    }

    @Override
    public void update(float dt) {
        handleInput();
      //  updateBackground();
        gameWorld.updateAll(dt);

        cam.position.x = playerCharacter.getPosition().x + 80;
        Gdx.gl.glClearColor(1, 0, 0, 1);
        gameManager.setMusic("flightStageMusic.mp3");

        Gdx.gl.glClearColor(1, 0, 0, 1);


        cam.update();

        for(int i=0; i<enemyArray.size; i++)
        {
            Enemy currentEnemy = enemyArray.get(i);



            for(int j=0; j<enemyArray.size; j++) {
                Enemy secondEnemy = enemyArray.get(j);

                if (j != i)
                {
                    if(currentEnemy.collides(secondEnemy.getBounds()))
                    {
                        System.out.println("collide var i: " + i + " j: " + j);
                        currentEnemy.setPosition(currentEnemy.getPosition());
                    }
                    //    if(currentEnemy.collides(secondEnemy.getBounds()) && playerCharacter.collides(currentEnemy.getBounds()))
                    // {
                    //     System.out.println("collide var i: " + i + " j: " + j);
                    //       currentEnemy.setPosition(currentEnemy.getPosition().add(-0.4f,-0.4f));
                    //   }
                    else
                    {
                        currentEnemy.chase(playerCharacter.getPosition().x, playerCharacter.getPosition().y);
                    }

                }
                currentEnemy.update(playerCharacter.getBounds());
                System.out.println("enemy velocity x: " + currentEnemy.getVelocity().x + " y: " + currentEnemy.getVelocity().y);
            }
        }

//            if(waveCleared)
//            {
//                sendNewWave();
//            }
    }

//    private void updateBackground(){
//        if(cam.position.x - (cam.viewportWidth / 2) > backgroundPos1.x + backgroundImage.getWidth())
//            backgroundPos1.add(gameWorld.backgroundImage.getWidth() * 2, 0);
//        if(cam.position.x - (cam.viewportWidth / 2) > backgroundPos2.x + backgroundImage.getWidth())
//            backgroundPos2.add(backgroundImage.getWidth() * 2, 0);
//
//    }
}
