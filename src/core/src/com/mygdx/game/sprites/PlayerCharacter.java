package com.mygdx.game.sprites;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import java.lang.reflect.Method;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sprites.enemies.Enemy;
import com.mygdx.game.sprites.powerups.*;

public class PlayerCharacter extends Character{
    private int mana, maxMana;
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
        maxMana = 100;
        mana = maxMana;
        meleeDirection = new Vector2(1,0);
    }

    public void moveUp(){ position.y += (speed / 2); }

    public void moveDown(){
        position.y -= (speed / 2);
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
        if(mana > maxHealth)
            mana = maxHealth;
    }

    public void decreaseMana(int value){
        mana = mana - value;
    }

    public void collectPowerup(Powerups powerup) {
        if(powerup instanceof HealthPotion){
            increaseHealth(((HealthPotion) powerup).getHealthIncrease());
        }
        else if(powerup instanceof ManaPotion){
            increaseMana(((ManaPotion) powerup).getManaIncrease());
        }
        else if(powerup instanceof Altar){
            health = maxHealth;
            mana = maxMana;
        }
        else if(powerup instanceof Ashbringer){
            damage = damage + 25;
        }
        else if(powerup instanceof JudgementArmor){
            maxHealth = maxHealth + JudgementArmor.getMaxHealthIncrease();
            damage = damage + JudgementArmor.getHolyLightIncrese();
            rightProfile = new Texture("JudgementArmorPaladinRight.png");
            leftProfile = new Texture("JudgementArmorPaladinLeft.png");
        }
        else if(powerup instanceof  LightswornArmor){
            maxHealth = maxHealth + LightswornArmor.getMaxHealthIncrease();
            damage = damage + LightswornArmor.getHolyLightDamage();
        }
        else{
            throw new IllegalArgumentException("No such powerup error!");

        }
    }
}
