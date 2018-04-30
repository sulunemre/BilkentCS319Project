package com.mygdx.game.sprites;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.sprites.enemies.Enemy;

import java.io.Serializable;

public class GameWorld implements Serializable {
    private static GameWorld gameWorld = new GameWorld();

    private Array<GameElement> gameElementsArray  = new Array<GameElement>();
    private PlayerCharacter playerCharacter;
    private Array<PlayerCharacter> playerCharacterArray;
    private Array<Rock> rocks;
    private Array<RockMoving> rocksMoving;
    private Array<Enemy> enemyArray;
    //private Array<Powerups> powerupsArray;
    private Array<HolyLight> playerProjectiles;
    private Array<HolyLight> enemyProjectiles;


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

    public Array<PlayerCharacter> getplayerCharacterArray() {
        return playerCharacterArray;
    }

    public Array<Rock> getRocks() {
        return rocks;
    }

    public void setRocks(Array<Rock> rocks) {
        this.rocks = rocks;
    }

    public Array<RockMoving> getRocksMoving() {
        return rocksMoving;
    }

    public void setRocksMoving(Array<RockMoving> rocksMoving) {
        this.rocksMoving = rocksMoving;
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

    public Array<HolyLight> getEnemyProjectiles(){
        return enemyProjectiles;
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
}
