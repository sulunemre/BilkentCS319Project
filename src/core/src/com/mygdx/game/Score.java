package com.mygdx.game;

import java.io.Serializable;

public class Score implements Comparable<Score>, Serializable{
    String playerName;
    double score;

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
}
