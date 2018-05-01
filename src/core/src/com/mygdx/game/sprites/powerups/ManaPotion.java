package com.mygdx.game.sprites.powerups;

import com.badlogic.gdx.graphics.Texture;

public class ManaPotion extends Powerups {
    private int manaIncrease;

    /**
     * type 0 25 mana pot
     * type 1 50 mana pot
     * type 2 75 mana pot
     */
    private int type;

    /**
     * @param x           Spawn location x coordinate
     * @param y           Spawn location y coordinate
     */
    public ManaPotion(float x, float y, int type) {
        super(x, y, "lesserManaPotion.png");

        String texturePath;
        if(type == 0) {
            texturePath = "lesserManaPotion.png";
            manaIncrease = 25;
        }
        else if(type == 1) {
            texturePath = "mediumManaPotion.png";
            manaIncrease = 50;
        }
        else {
            texturePath = "largerManaPotion.png";
            manaIncrease = 75;
        }

        elementTexture = new Texture(texturePath);
        this.type = type;
    }

    public int getManaIncrease() {
        return manaIncrease;
    }

    public int getType() {
        return type;
    }
}
