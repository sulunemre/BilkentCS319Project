package com.mygdx.game.stateControllers;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GameManager;
import com.mygdx.game.sprites.*;
import com.mygdx.game.sprites.enemies.Enemy;
import com.mygdx.game.sprites.enemies.EnemyFactory;
import com.mygdx.game.sprites.enemies.Grunt;
import com.mygdx.game.states.PlayState;



public class FightStateController extends AbstractStateController {
    private GameWorld gameWorld;
    private GameManager gameManager;
    private PlayerCharacter playerCharacter;
    private Array<Enemy> enemyArray;
    private int wave;
    private boolean waveCleared;
    private Texture backgroundImage;

    private Array<Rock> rocks;
    private Array<RockMoving> rocksM;
    private static final int ROCK_SPACING = 125;
    private static final int ROCK_COUNT = 3;
    private static final int BACKGROUND_Y_OFFSET = -30;
    private Vector2 backgroundPos1, backgroundPos2;

    public FightStateController() {
        gameWorld = GameWorld.getInstance();
        gameManager = GameManager.getInstance();
        playerCharacter = gameWorld.getPlayerCharacter();
        enemyArray = gameWorld.getEnemyArray();
        backgroundImage = new Texture("background1.png");
        wave = 1;
        waveCleared = true;

        rocks = new Array<Rock>();
        rocksM = new Array<RockMoving>();
        for(int i =1; i < ROCK_COUNT; i++){

            rocks.add(new Rock(i * (ROCK_SPACING + Rock.TUBE_WIDTH), 5));
        }

        backgroundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, BACKGROUND_Y_OFFSET);
        backgroundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + backgroundImage.getWidth(), BACKGROUND_Y_OFFSET);
        gameWorld.setBackgroundPos1(backgroundPos1);
        gameWorld.setBackgroundPos2(backgroundPos2);

        playerCharacter.setDirection(new Vector2(0,0)); // At the beginning of the state, player stops
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
        if(input.isKeyPressed(Input.Keys.A)){
            playerCharacter.moveLeft();
        }
        if(input.isKeyPressed(Input.Keys.D)){
            playerCharacter.moveRight();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.Q)) {
            gameStateManager.set(new PlayState());
        }

        if(input.justTouched()){
            // Click coordinate system is different from game world coordinates
            // Firstly, they are transformed.
            Vector3 unprojected3DClick = cam.unproject(new Vector3(input.getX(), input.getY(), 0));
            Vector2 unprojected2DClick = new Vector2(unprojected3DClick.x, unprojected3DClick.y);

            // Calculate the direction
            HolyLight holyLight = new HolyLight(playerCharacter.getPosition().x, playerCharacter.getPosition().y, playerCharacter.getDamage());
            Vector2 holyLightDirection = unprojected2DClick.mulAdd(playerCharacter.getPosition(), -1).nor();
            holyLight.setDirection(holyLightDirection);

            // Add holy light to the game world
            gameWorld.addGameElements(holyLight);
            gameWorld.addPlayerProjectile(holyLight);
        }

    }

    @Override
    public void update(float dt) {

        handleInput();
        updateBackground();
        gameWorld.updateAll(dt);

        cam.position.x = playerCharacter.getPosition().x + 80;
        Gdx.gl.glClearColor(1, 0, 0, 1);
        //&& !gameManager.isSecondClicked()
        if(!gameManager.isFirstClicked() )
        {

            //gameManager.getCurrentMusic().pause();
            gameManager.setMusic("flightStageMusic.mp3");
            gameManager.getCurrentMusic().setLooping(true);
            gameManager.getCurrentMusic().play();
        }


        Gdx.gl.glClearColor(1, 0, 0, 1);


        cam.update();



        if(waveCleared) {
            sendNewWave();
        }
        for(Enemy enemy : enemyArray){
            enemy.chase( gameWorld.getPlayerCharacter().getPosition().x , gameWorld.getPlayerCharacter().getPosition().y);
        }
        if (gameWorld.getPlayerCharacter().getPosition().x < 0)
            gameWorld.getPlayerCharacter().getPosition().x = 0;
        if (gameWorld.getPlayerCharacter().getPosition().x > 500)
            gameWorld.getPlayerCharacter().getPosition().x = 500;
        collisionTrigger();

        // Remove dead enemies
        for(Enemy enemy : enemyArray){
            if(enemy.getHealth() <= 0){
                gameWorld.removeGameElements(enemy);
                enemyArray.removeValue(enemy, false);
            }
        }

        // If all enemies are dead, send new wave
        if(enemyArray.size == 0)
            waveCleared = true;

        System.out.println(wave);
    }

   private void updateBackground(){
        if(cam.position.x - (cam.viewportWidth / 2) > backgroundPos1.x + backgroundImage.getWidth())
            backgroundPos1.add(backgroundImage.getWidth() * 2, 0);
        if(cam.position.x - (cam.viewportWidth / 2) > backgroundPos2.x + backgroundImage.getWidth())
            backgroundPos2.add(backgroundImage.getWidth() * 2, 0);

    }

    private void sendNewWave() {

        int gruntCount = wave * 5;
        int skeletonWarriorCount = wave * 3;

        EnemyFactory enemyFactory = new EnemyFactory();

        for(int i=0; i<gruntCount; i++){
            float yLocation = (float) Math.random()*260;
            Grunt grunt = (Grunt) enemyFactory.getEnemy("grunt", 0, yLocation);


            grunt.setDirection(new Vector2(1,0));
            enemyArray.addAll(grunt);
            gameWorld.addGameElements(grunt);
        }

        waveCleared = false;
        wave++;
    }

    public void collisionTrigger(){
        for(HolyLight pp : gameWorld.getPlayerProjectiles()) {
            for(Enemy enemy : gameWorld.getEnemyArray()) {
                if (pp.getBounds().overlaps(enemy.getBounds())){
                    enemy.reduceHealth(pp.getDamage());
                    gameWorld.removeGameElements(pp);
                    gameWorld.getPlayerProjectiles().removeValue(pp,false);
                }
            }
        }
        for(HolyLight ep : gameWorld.getEnemyProjectiles()) {
            for(PlayerCharacter player : gameWorld.getplayerCharacterArray()) {
                if (ep.collision(player.getBounds())){
                    player.reduceHealth(ep.getDamage());
                    gameWorld.removeGameElements(ep);
                }
            }
        }
        /**
         * Powerups need to extend GameElement.
         */
        /*for(Powerups powerup: gameWorld.getPowerups()) {
            if (powerup.collision(playerCharacter.getBounds()))
                gameStateManager.set(new MenuState());
                gameWorld.removeGameElements(powerup);
        }*/
    }

    public boolean moveCheck(GameElement element, float x, float y){
        Rectangle temp = new Rectangle (element.getBounds());
        temp.setPosition( temp.getX() + x, temp.getY() + y);

        for (Enemy enemy : gameWorld.getEnemyArray()){
            if ( enemy != element) {
                if (enemy.collision(temp))
                    return false;
            }
        }
        for (Rock rock : gameWorld.getRocks()){
            if ( rock.collision(temp))
                return false;
        }
        for (PlayerCharacter player : gameWorld.getplayerCharacterArray()){
            if ( player != element) {
                if (player.collision(temp))
                    return false;
            }
        }
        return true;
    }

    public int getWave() {
        return wave;
    }
}
