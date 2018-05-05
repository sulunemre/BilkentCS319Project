package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;

public class HolyLight extends GameElement {

    private int damage;

    public HolyLight(float x, float y, int damage){
        super(x, y, "holylight.png");
        speed = 15;
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
