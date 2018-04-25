package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import java.io.IOException;

public class GameManager {
    private static GameManager gameManager = new GameManager();

    private FileSystemManager fileSystemManager = FileSystemManager.getInstance();

    private String playerName;
    private Music currentMusic;
    private double score;

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
}