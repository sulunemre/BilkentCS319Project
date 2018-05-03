package com.mygdx.game.sprites.powerups;


import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.sprites.PlayerCharacter;

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
        super(x, y, "lesserHealthPotion.png");
        this.type = type;

        String texturePath;
        if(type == 0) {
            texturePath = "lesserHealthPotion.png";
            healthIncrease = 25;
        }
        else if(type == 1) {
            texturePath = "mediumHealthPotion.png";
            healthIncrease = 50;
        }
        else {
            texturePath = "largerHealthPotion.png";
            healthIncrease = 100;
        }

        elementTexture = new Texture(texturePath);

    }

    public int getHealthIncrease() {
        return healthIncrease;
    }

    public void activate(PlayerCharacter player) {
        player.setHealth( healthIncrease);
    }
}
