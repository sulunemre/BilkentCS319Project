package com.mygdx.game;

import java.io.Serializable;

public class Score implements Comparable<Score>, Serializable{
    private String playerName;
    private double score;
    private static final long serialVersionUID = -7201852659754802949L; // Something about serializing, I don't know


    public Score(String playerName, double score) {
        this.playerName = playerName;
        this.score = score;
    }

    @Override
    public int compareTo(Score scoreToBeCompared) {
        if(this.score > scoreToBeCompared.score)
            return 1;
        else if(this.score < scoreToBeCompared.score)
            return -1;
        else
            return 0;
    }

    @Override
    public String toString() {
        return playerName + " " + score;
    }
}
