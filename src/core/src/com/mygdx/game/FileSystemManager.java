package com.mygdx.game;

import com.mygdx.game.sprites.GameWorld;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
    public void saveGameWorldToTxt(GameWorld gw) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("savedGameWorld.txt"));
        oos.writeObject(gw);
    }

    /**
     * Reads txt file and returns a game world
     * @return
     */
    public GameWorld loadGameWorldFromTxt() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream((new FileInputStream("savedGameWorld")));
        return (GameWorld) ois.readObject();
    }

    /**
     * Writes a score to the end of txt file
     * @param score score record that holds name and score
     */
    public void saveScoreToTxt(Score score) throws IOException, ClassNotFoundException {
        //Convert txt to list and append the score
        ObjectInputStream ois = new ObjectInputStream((new FileInputStream("highScores.txt")));
        List<Score> highScoreList = (List<Score>) ois.readObject();
       // List<Score> highScoreList = new ArrayList<Score>(); //TODO: test için yazıldı, sil!
        highScoreList.add(score);


        // Write new highScoreList to txt
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("highScores.txt"));
        oos.writeObject(highScoreList);
    }

    /**
     * Read txt file and returns the array of scores
     * @return
     */
    public List<Score> loadHighScoreTableFromTxt() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream((new FileInputStream("highScores.txt")));
        return (List<Score>) ois.readObject();
    }
}
