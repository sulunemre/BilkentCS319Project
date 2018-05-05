package com.mygdx.game.sprites;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import java.lang.reflect.Method;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sprites.enemies.Enemy;
import com.mygdx.game.sprites.powerups.*;

public class PlayerCharacter extends Character{
    private int mana, maxMana;

    public static Texture getRightProfile() {
        return rightProfile;
    }

    public static Texture getLeftProfile() {
        return leftProfile;
    }

    private static Texture rightProfile = new Texture("paladin.png");
    private static Texture leftProfile = new Texture("paladinReverse.png");

    public static Texture getRightProfileAttack() {
        return rightProfileAttack;
    }

    public static Texture getLeftProfileAttack() {
        return leftProfileAttack;
    }

    private static Texture rightProfileAttack = new Texture("paladinAttackRight.png");
    private static Texture leftProfileAttack = new Texture("paladinAttackLeft.png");
    private static Texture rightProfileJudgementArmor = new Texture("paladin.png");
    private static Texture leftProfileJudgementArmor = new Texture("paladinReverse.png");
    private static Texture rightProfileJudgementArmorAttack = new Texture("paladin.png");
    private static Texture leftProfileJudgementArmorAttack = new Texture("paladinReverse.png");
    private static Texture rightProfileLightswornArmor = new Texture("paladin.png");
    private static Texture leftProfileLightswornArmor = new Texture("paladinReverse.png");
    private static Texture rightProfileLightswornArmorAttack = new Texture("paladin.png");
    private static Texture leftProfileLightswornArmorAttack = new Texture("paladinReverse.png");
    private Vector2 meleeDirection;
    private int armorType;

    public Vector2 getMeleeDirection() {
        return meleeDirection;
    }

    public void setMeleeDirection(Vector2 meleeDirection) {
        this.meleeDirection = meleeDirection;
    }

    public PlayerCharacter(float x, float y){
        super(x, y, "paladin.png",100);
        armorType = 0;
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

    public void moveRight() {
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
            damage = damage + Ashbringer.getMeleeDamageIncrease();
            elementTexture = new Texture("paladinWithAshbringerRight.png");
            rightProfile = new Texture("paladinWithAshbringerRight.png");
            leftProfile = new Texture("paladinWithAshbringerLeft.png");
            rightProfileAttack = new Texture("paladinWithAshbringerAttackRight.png");
            leftProfileAttack = new Texture("paladinWithAshbringerAttackLeft.png");
        }
        else if(powerup instanceof JudgementArmor){
            maxHealth = maxHealth + JudgementArmor.getMaxHealthIncrease();
            damage = damage + JudgementArmor.getHolyLightIncrese();
            elementTexture = new Texture("JudgementArmorPaladinRight.png");
            rightProfile = new Texture("JudgementArmorPaladinRight.png");
            leftProfile = new Texture("JudgementArmorPaladinLeft.png");
            rightProfileAttack = new Texture("JudgementArmorPaladinAttackRight.png");
            leftProfileAttack = new Texture("JudgementArmorPaladinAttackLeft.png");
        }
        else if(powerup instanceof  LightswornArmor){
            maxHealth = maxHealth + LightswornArmor.getMaxHealthIncrease();
            damage = damage + LightswornArmor.getHolyLightDamage();
            elementTexture = new Texture("LightSwornArmorPaladinRight.png");
            rightProfile = new Texture("LightSwornArmorPaladinRight.png");
            leftProfile = new Texture("LightSwornArmorPaladinLeft.png");
            rightProfileAttack = new Texture("LightSwornArmorPaladinAttackRight.png");
            leftProfileAttack = new Texture("LightSwornArmorPaladinAttackLeft.png");
        }
        else{
            throw new IllegalArgumentException("No such powerup error!");

        }
    }
}
