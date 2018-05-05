package com.mygdx.game.sprites;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.sprites.enemies.Enemy;
import com.mygdx.game.sprites.powerups.Powerups;

import java.io.Serializable;

public class GameWorld implements Serializable {
    private static GameWorld gameWorld = new GameWorld();

    private Array<GameElement> gameElementsArray  = new Array<GameElement>();
    private PlayerCharacter playerCharacter;
    private Array<PlayerCharacter> playerCharacterArray = new Array<PlayerCharacter>();
    private Array<Rock> rocks = new Array<Rock>();;
    private Array<Enemy> enemyArray = new Array<Enemy>();;
    private Array<Powerups> powerupsArray = new Array<Powerups>();
    private Array<HolyLight> playerProjectiles = new Array<HolyLight>();
    private Array<HolyLight> enemyProjectiles = new Array<HolyLight>();


    private Vector2 backgroundPos1, backgroundPos2;

    public static GameWorld getInstance() {
        return gameWorld;
    }

    private GameWorld() {
    }

    /**
     * Adds one or more GameElements to the array
     * @param ge
     */
    public void addGameElements(GameElement... ge){
        gameElementsArray.addAll(ge);
    }

    /**
     * Removes a GameElement from the array
     * @param ge
     */
    public void removeGameElements(GameElement ge){
        gameElementsArray.removeValue(ge, false);
    }

    public Array<GameElement> getGameElementsArray(){
        return gameElementsArray;
    }

    public void updateAll(float dt){
        for(GameElement ge : gameElementsArray){
            ge.update(dt);
        }
    }

    public PlayerCharacter getPlayerCharacter() {
        return playerCharacter;
    }

    public void setPlayerCharacter(PlayerCharacter playerCharacter) {
        this.playerCharacter = playerCharacter;
    }

    public Array<PlayerCharacter> getPlayerCharacterArray() { return playerCharacterArray; }

    public void addPlayerCharacter(PlayerCharacter... player) { playerCharacterArray.addAll(player); }

    public Array<Rock> getRocks() {
        return rocks;
    }

    public void setRocks(Array<Rock> rocks) {
        this.rocks = rocks;
    }

    public Array<Enemy> getEnemyArray() {
        return enemyArray;
    }

    public void setEnemyArray(Array<Enemy> enemyArray) {
        this.enemyArray = enemyArray;
    }

    public Array<HolyLight> getPlayerProjectiles(){
        return playerProjectiles;
    }

    public void addPlayerProjectile(HolyLight... projectile){
        playerProjectiles.addAll(projectile);
    }

    public Array<HolyLight> getEnemyProjectiles(){
        return enemyProjectiles;
    }

    public void addEnemyProjectile(HolyLight... projectile){
        enemyProjectiles.addAll(projectile);
    }

    public Vector2 getBackgroundPos1() {
        return backgroundPos1;
    }

    public void setBackgroundPos1(Vector2 backgroundPos1) {
        this.backgroundPos1 = backgroundPos1;
    }

    public Vector2 getBackgroundPos2() {
        return backgroundPos2;
    }

    public void setBackgroundPos2(Vector2 backgroundPos2) {
        this.backgroundPos2 = backgroundPos2;
    }

    public Array<Powerups> getPowerups() {
        return powerupsArray;
    }

    public void removeEverything(){
        gameElementsArray.clear();
        playerCharacterArray.clear();
        rocks.clear();
        enemyArray.clear();
        powerupsArray.clear();
        playerProjectiles.clear();
        enemyProjectiles.clear();
    }

    public void removeRocks (){
        for (Rock rock : rocks){
            gameElementsArray.removeValue(rock, false);
        }
        rocks.clear();
    }
}
