package com.mygdx.game.sprites;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import java.lang.reflect.Method;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sprites.enemies.Enemy;

public class PlayerCharacter extends Character{
    private int mana;
    private static Texture rightProfile = new Texture("paladin.png");
    private static Texture leftProfile = new Texture("paladinReverse.png");
    private Vector2 meleeDirection;

    public Vector2 getMeleeDirection() {
        return meleeDirection;
    }

    public void setMeleeDirection(Vector2 meleeDirection) {
        this.meleeDirection = meleeDirection;
    }

    public PlayerCharacter(float x, float y){
        super(x, y, "paladin.png",100);
        damage = 25;
        speed = 10;
        mana = 100;
        meleeDirection = new Vector2(1,0);
    }

    public int getHealth()
    {
        return maxHealth;
    }

    public void setHealth(int increase)
    {
        if (health + increase < maxHealth)
            health = health + increase;
        else
            health = maxHealth;
    }

    public void moveUp(){ position.y += (speed / 10); }

    public void moveDown(){
        position.y -= (speed / 10);
    }

    public void moveRight(){
        position.x += speed;
        elementTexture = rightProfile;
    }

    public void moveLeft(){
        position.x -= speed;
        elementTexture = leftProfile;
    }

    public boolean collides(Rectangle enemy){
        return enemy.overlaps(bounds);
    }

    public int getMana(){
        return mana;
    }

    public void increaseMana(int value){
        mana = mana + value;
    }

    public void decreaseMana(int value){
        mana = mana - value;
    }
}
