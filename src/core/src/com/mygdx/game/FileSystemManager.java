package com.mygdx.game;

import com.mygdx.game.sprites.GameWorld;

public class FileSystemManager {
    private static FileSystemManager fileSystemManager = new FileSystemManager();

    public static FileSystemManager getInstance() {
        return fileSystemManager;
    }

    private FileSystemManager() {
    }

    /**
     * Writes game world details to the txt
     * @param gw GameWorld object to be saved
     */
    public void saveGameWorldToTxt(GameWorld gw){
        //TODO: implement
    }

    /**
     * Reads txt file and returns a game world
     * @return
     */
    public GameWorld loadGameWorldFromTxt(){
        //TODO: implement
        return null;
    }

    /**
     * Writes a score to the end of txt file
     * @param score score record that holds name and score
     */
    public void saveScoreToTxt(Score score){
        //TODO: implement
    }

    /**
     * Read txt file and returns the arrray of scores
     * @return
     */
    public Score[] loadHighScoreTableFromTxt(){
        //TODO: implement
        return null;
    }
}
