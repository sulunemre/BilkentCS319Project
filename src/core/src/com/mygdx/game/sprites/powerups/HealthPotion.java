package com.mygdx.game.sprites.powerups;


import com.badlogic.gdx.graphics.Texture;

public class HealthPotion extends Powerups {
    private int healthIncrease;

    /**
     * type 0 25 hp pot
     * type 1 50 hp pot
     * type 2 75 hp pot
     */
    private int type;

    /**
     * @param x           Spawn location x coordinate
     * @param y           Spawn location y coordinate
     */
    public HealthPotion(float x, float y, int type) {
        super(x, y, "healthPotion.png");

        String texturePath;
        if(type == 0) {
            texturePath = "lesserPot.png";
            healthIncrease = 25;
        }
        else if(type == 1) {
            texturePath = "mediumPot.png";
            healthIncrease = 50;
        }
        else {
            texturePath = "largerPot.png";
            healthIncrease = 100;
        }

        elementTexture = new Texture(texturePath);
        this.type = type;
    }

    public int getHealthIncrease() {
        return healthIncrease;
    }
}
