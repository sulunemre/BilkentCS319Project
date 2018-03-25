package com.mygdx.game.sprites.powerups;

public class Altar extends Powerups {
    private int type;

    public Altar(int spawnRate, int type) {
        super(spawnRate);
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
