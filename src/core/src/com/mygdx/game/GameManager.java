package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.mygdx.game.sprites.GameWorld;

import java.io.IOException;

public class GameManager {
    private static GameManager gameManager = new GameManager();

    private FileSystemManager fileSystemManager = FileSystemManager.getInstance();
    private GameWorld gameWorld = GameWorld.getInstance();

    private String playerName;
    private Music currentMusic;
    private double score;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private int count=0;
    private boolean firstClicked=false;
    private boolean secondClicked=false;



    public boolean isSecondClicked() {
        return secondClicked;
    }

    public void setSecondClicked(boolean secondClicked) {
        this.secondClicked = secondClicked;
    }


    public boolean isFirstClicked() {
        return firstClicked;
    }

    public void setFirstClicked(boolean firstClicked) {
        this.firstClicked = firstClicked;
    }



    public static GameManager getInstance() {
        return gameManager;
    }

    private GameManager() {
    }

    public void saveHighScore() throws IOException, ClassNotFoundException {
        Score scoreToBeSaved = new Score(playerName, score);
        fileSystemManager.saveScoreToTxt(scoreToBeSaved);
    }

    public void loadHighScoreTable(){
        //TODO: implement
    }

    /**
     * Mutes the music of the game.
     */
    public void muteMusic(){
        currentMusic.setVolume(0);
    }

    /**
     * Reactivate the music.
     */
    public void unmuteMusic(){
        currentMusic.setVolume(1);
    }

    /**
     * Changes the music.
     * @param musicPath name of the music file in the assets folder
     */
    public void setMusic(String musicPath){
        currentMusic = Gdx.audio.newMusic(Gdx.files.internal(musicPath));
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Music getCurrentMusic() {
        return currentMusic;
    }

    public void setCurrentMusic(Music currentMusic) {
        this.currentMusic = currentMusic;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void increseScore(int increaseAmount){
        score = score + increaseAmount;
    }

    public void gameOver(){
        //TODO: implement
    }
}