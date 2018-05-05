package com.mygdx.game.stateControllers;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GameManager;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.*;
import com.mygdx.game.sprites.enemies.*;
import com.mygdx.game.sprites.powerups.PowerupFactory;
import com.mygdx.game.sprites.powerups.Powerups;
import com.mygdx.game.states.GameOverState;
import com.mygdx.game.states.MenuState;
import com.mygdx.game.states.PlayState;
import com.mygdx.game.states.State;


public class FightStateController extends AbstractStateController {
    private GameWorld gameWorld;
    private GameManager gameManager;
    private PlayerCharacter playerCharacter;
    private Array<Enemy> enemyArray;
    private int wave;
    private boolean waveCleared;
    private Texture backgroundImage;
    private int initialPos;
    private static final int BACKGROUND_Y_OFFSET = -30;
    private Vector2 backgroundPos1, backgroundPos2;
    private Sound effect;
    private Texture paladinAttackRight;
    private int coolDownTimer;
    private String playerSentence;
    private static String[] waveClearedSentences = new String[]{"Victory!", "I killed them all", "In the name of the Light \nI have defeated my enemies", "You bastards!"};
    private String waveClearedSentence;
    private String waveIncomingSentence;
    private static String[] waveIncomingSentences = new String[]{"Oh no! Here they come again!", "fuk this shit m8", "What else can they put in my way?", "Light... Help me...", "Do they ever stop coming?"};

    public FightStateController(State state) {
        super(state);
        gameWorld = GameWorld.getInstance();
        gameManager = GameManager.getInstance();
        playerCharacter = gameWorld.getPlayerCharacter();
        enemyArray = gameWorld.getEnemyArray();
        backgroundImage = new Texture("background1.png");
        paladinAttackRight = new Texture("paladinAttackRight.png");
        effect=Gdx.audio.newSound(Gdx.files.internal("holylightCast.mp3"));
        coolDownTimer = 100;
        wave = 0;
        waveCleared = true;
        initialPos = (int)playerCharacter.getPosition().x;
        playerSentence = "";

        backgroundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, BACKGROUND_Y_OFFSET);
        backgroundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + backgroundImage.getWidth(), BACKGROUND_Y_OFFSET);
        gameWorld.setBackgroundPos1(backgroundPos1);
        gameWorld.setBackgroundPos2(backgroundPos2);

        playerCharacter.setDirection(new Vector2(0,0)); // At the beginning of the state, player stops
        if(gameManager.getCount()%2==0)
        {
            gameManager.getCurrentMusic().pause();
            gameManager.setMusic("fightStateMusic.mp3");
            gameManager.getCurrentMusic().setVolume(0.8f);
        }
    }

    @Override
    public void handleInput() {
        Input input = Gdx.input;

         if(input.isKeyPressed(Input.Keys.W)){
             playerCharacter.setSpeed(10);
           //  playerCharacter.setDirection(new Vector2(0,1));
            if ( moveCheck(playerCharacter, 0, 10)) {
                playerCharacter.moveUp();
            }
        }
          if(input.isKeyPressed(Input.Keys.S)){
             playerCharacter.setSpeed(10);
          //   playerCharacter.setDirection(new Vector2(0,-1));
            if ( moveCheck(playerCharacter, 0, -10)){
                playerCharacter.moveDown();
            }
         }
          if(input.isKeyPressed(Input.Keys.A)){


             playerCharacter.setMeleeDirection(new Vector2(-1,0));
            if ( moveCheck(playerCharacter, -10, 0)) {
                playerCharacter.moveLeft();
            }
            }
          if(input.isKeyPressed(Input.Keys.D)){
             playerCharacter.setSpeed(10);
          //   playerCharacter.setDirection(new Vector2(1,0));
              playerCharacter.setMeleeDirection(new Vector2(1,0));
            if ( moveCheck(playerCharacter, 10, 0)) {
                playerCharacter.moveRight();
            }
         }
//        else{
//           playerCharacter.setSpeed(0);
//         }

        if(input.isKeyPressed(Input.Keys.Q) && waveCleared) {
            gameStateManager.set(new PlayState());
        }
        else if(input.isKeyPressed(Input.Keys.Q) && !waveCleared){
             playerSentence = "I can't run away without killing them all!";
        }
        else{
             playerSentence = "";
        }

        if(input.justTouched()){
            // Click coordinate system is different from game world coordinates
            // Firstly, they are transformed.
            Vector3 unprojected3DClick = cam.unproject(new Vector3(input.getX(), input.getY(), 0));
            Vector2 unprojected2DClick = new Vector2(unprojected3DClick.x, unprojected3DClick.y);
            if(gameWorld.getPlayerCharacter().getMana() >= 10) {
                // Calculate the direction
                HolyLight holyLight = new HolyLight(playerCharacter.getPosition().x, playerCharacter.getPosition().y, playerCharacter.getDamage());
                Vector2 holyLightDirection = unprojected2DClick.mulAdd(playerCharacter.getPosition(), -1).nor();
                holyLight.setDirection(holyLightDirection);

                // Add holy light to the game world
                gameWorld.addGameElements(holyLight);
                gameWorld.addPlayerProjectile(holyLight);
                effect.play();
                gameWorld.getPlayerCharacter().decreaseMana(10);
                cam.setToOrtho(false, MyGdxGame.WIDTH  , MyGdxGame.HEIGHT  );
            }
        }
        // Melee attack implementation
        if(input.isKeyJustPressed(Input.Keys.SPACE)){
            Vector2 circleCenterCoordinates;
            if(playerCharacter.getMeleeDirection().x < 0)
                playerCharacter.setElementTexture(PlayerCharacter.getLeftProfileAttack());
            else
                playerCharacter.setElementTexture(PlayerCharacter.getRightProfileAttack());


            if(playerCharacter.getMeleeDirection().x < 0)
                circleCenterCoordinates = new Vector2(playerCharacter.getPosition().x, playerCharacter.getPosition().y + 10);
            else
                circleCenterCoordinates = new Vector2(playerCharacter.getPosition().x + playerCharacter.getBounds().width, playerCharacter.getPosition().y + 10);

                int meleeRange = 10;
                Circle meleeRangeBounds = new Circle(circleCenterCoordinates, meleeRange);

                for(Enemy enemy : enemyArray){
                    Rectangle enemyBound = enemy.getBounds();
                    if(Intersector.overlaps(meleeRangeBounds, enemyBound)){
                        enemy.reduceHealth(10);
                        System.out.println("melee oldu");
                    }
                }

            }
            else{
                if(playerCharacter.getMeleeDirection().x < 0)
                    playerCharacter.setElementTexture(playerCharacter.getLeftProfile());
                else
                    playerCharacter.setElementTexture(playerCharacter.getRightProfile());
         }
        }



    @Override
    public void update(float dt) {

        handleInput();
        updateBackground();
        gameWorld.updateAll(dt);

        cam.position.x = playerCharacter.getPosition().x + 80;
        Gdx.gl.glClearColor(1, 0, 0, 1);
        if(gameManager.getCount()%2==0)
        {
            gameManager.getCurrentMusic().setLooping(true);
            gameManager.getCurrentMusic().play();
        }


        Gdx.gl.glClearColor(1, 0, 0, 1);


        cam.update();



        if(waveCleared) {
            coolDownTimer--;
            if(coolDownTimer > 300){
                playerSentence = waveClearedSentence;
            }
            else if(coolDownTimer < 200 && wave != 0){
                playerSentence = waveIncomingSentence;
            }

            if(coolDownTimer % 100 == 0){
                createPowerup(0);
            }
        }
        else{
            coolDownTimer = 500;
        }
        if(coolDownTimer <= 0){
            wave++;
            sendNewWave();}
        System.out.println("cooldown: " + coolDownTimer);

        //for(Enemy enemy : enemyArray){
        for ( int i = 0; i < enemyArray.size; i++){
            enemyArray.get(i).chase( gameWorld.getPlayerCharacter().getPosition().x , gameWorld.getPlayerCharacter().getPosition().y);
            if (moveCheck(enemyArray.get(i))){
            }
            else
                enemyArray.get(i).setDirection(0,0);
        }

        if (gameWorld.getPlayerCharacter().getPosition().x < initialPos - 100)
            gameWorld.getPlayerCharacter().getPosition().x = initialPos - 100;
        if (gameWorld.getPlayerCharacter().getPosition().x > initialPos + 500)
            gameWorld.getPlayerCharacter().getPosition().x = initialPos + 500;
        collisionTrigger();

        // Remove dead enemies
        for(Enemy enemy : enemyArray){
            if(enemy.getHealth() <= 0){
                gameManager.increseScore(enemy.getKillingReward());
                gameWorld.removeGameElements(enemy);
                enemyArray.removeValue(enemy, false);
            }
        }

        // If all enemies are dead, send new wave
        if(enemyArray.size == 0)
            waveCleared = true;

        // Attacks of the enemies
        for(Enemy enemy : enemyArray){
            enemy.decrementCooldown();
            System.out.println(enemy.getCoolDown());
            if(enemy.getCoolDown() == 0){
                enemy.attack(playerCharacter);
            }
        }

        // Check player's health, if it is <= 0 game over
        if(!playerCharacter.isAlive()){
            gameManager.getCurrentMusic().pause();
            gameManager.gameOver();
        }

        for (HolyLight projectile : gameWorld.getEnemyProjectiles()){
            if ( projectile.getPositionx() <= initialPos - 100 || projectile.getPositionx() >= initialPos + 500 || projectile.getPositiony() <= 0 || projectile.getPositiony() >= 400)
                gameWorld.removeGameElements(projectile);
        }

        for (HolyLight projectile : gameWorld.getPlayerProjectiles()){
            if ( projectile.getPositionx() <= initialPos - 100 || projectile.getPositionx() >= initialPos + 500 || projectile.getPositiony() <= 0 || projectile.getPositiony() >= 400)
                gameWorld.removeGameElements(projectile);
        }
//        for(Enemy enemy : gameWorld.getEnemyArray()){
//            if(enemy.)
//        }

    }

   private void updateBackground(){
        if(cam.position.x - (cam.viewportWidth / 2) > backgroundPos1.x + backgroundImage.getWidth())
            backgroundPos1.add(backgroundImage.getWidth() * 2, 0);
        if(cam.position.x - (cam.viewportWidth / 2) > backgroundPos2.x + backgroundImage.getWidth())
            backgroundPos2.add(backgroundImage.getWidth() * 2, 0);

    }

    private void sendNewWave() {
        int leftEdge = initialPos - 100;
        int rightEdge = initialPos + 300;
      //  int

        EnemyFactory enemyFactory = new EnemyFactory();

        // In all levels, there are skeleton warriors and grunts
        int enemyCount = wave * 5;
        float yLocation = 0;
        for (int i = 0; i < enemyCount; i++) {
           // float yLocation = (float) Math.random() * 260;

            int random = (int) (Math.random() * 2);
            Enemy enemy;
            if (random == 0)
                enemy = enemyFactory.getEnemy("skeleton_warrior", leftEdge, yLocation);
            else
                enemy = enemyFactory.getEnemy("skeleton_warrior", leftEdge, yLocation);

            enemy.setDirection(new Vector2(1, 0));
            enemyArray.addAll(enemy);
            gameWorld.addGameElements(enemy);
            yLocation = yLocation + 50;
        }

        if(wave > 3){
            int abominationCount = (int) (Math.random() * wave);
            for(int i=0; i < abominationCount; i++){
                yLocation = (float) Math.random() * 260;
                Enemy enemy = enemyFactory.getEnemy("abomination", rightEdge, yLocation);

                enemy.setDirection(new Vector2(-1, 0));
                enemyArray.addAll(enemy);
                gameWorld.addGameElements(enemy);
            }
        }

        waveClearedSentence = randomPick(waveClearedSentences);
        waveIncomingSentence = randomPick(waveIncomingSentences);
        waveCleared = false;
    }

    public void collisionTrigger(){
        // Colision between player projectile and enemy
        for(HolyLight pp : gameWorld.getPlayerProjectiles()) {
            for(Enemy enemy : gameWorld.getEnemyArray()) {
                if (pp.getBounds().overlaps(enemy.getBounds())){
                    enemy.reduceHealth(pp.getDamage());
                    gameWorld.removeGameElements(pp);
                    gameWorld.getPlayerProjectiles().removeValue(pp,true);
                }
            }
        }
        // Colision between enemy projectile and player
        for(HolyLight ep : gameWorld.getEnemyProjectiles()) {
                if (ep.collision(playerCharacter.getBounds())){
                    playerCharacter.reduceHealth(ep.getDamage());
                    gameWorld.removeGameElements(ep);
                    gameWorld.getEnemyProjectiles().removeValue(ep,true);
                }
        }
        // Colision between powerup and player
        for(Powerups powerup : gameWorld.getPowerups()) {
            if (powerup.collision(playerCharacter.getBounds())) {
                playerCharacter.collectPowerup(powerup);
                gameWorld.removeGameElements(powerup);
                gameWorld.getPowerups().removeValue(powerup, true);
            }
        }
    }

   public boolean moveCheck(Enemy element){
        Rectangle temp = new Rectangle (element.getBounds());
        Vector2 newVelocity = (element.getDirection()).scl((float)element.getSpeed());
        temp.setPosition( temp.getX() + newVelocity.x, temp.getY() + newVelocity.y);

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
        for (PlayerCharacter player : gameWorld.getPlayerCharacterArray()){
                if (player.collision(temp))
                    return false;
        }
        return true;
    }

    public boolean moveCheck(PlayerCharacter element, float x, float y){
        Rectangle temp = new Rectangle (element.getBounds());
        temp.setPosition( temp.getX() + x, temp.getY() + y);

        for (Enemy enemy : gameWorld.getEnemyArray()){
                if (enemy.collision(temp))
                    return false;
        }
        for (Rock rock : gameWorld.getRocks()){
            if ( rock.collision(temp))
                return false;
        }
        for (PlayerCharacter player : gameWorld.getPlayerCharacterArray()){
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

    public String getPlayerSentence() {
        return playerSentence;
    }

    private String randomPick(String[] strings){
        return strings[(int)(System.currentTimeMillis() % strings.length)];
    }

    /**
     * @param level 0 for low level, 1 for medium, 2 for high level powerups
     */
    private void createPowerup(int level){
        int yLocation = (int) (Math.random()*260);
        int xLocation = (int) (Math.random()*400 + (initialPos - 100));
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
