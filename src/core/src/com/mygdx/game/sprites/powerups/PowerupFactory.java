package com.mygdx.game.sprites.powerups;

public class PowerupFactory {
    public Powerups getLowLevelPowerup(){
        int random = (int) (Math.random() * 3);

        if(random == 0)
            return new LightswornArmor(0,0);
        else if(random == 1)
            return new ManaPotion(0,0,0);
        else if(random == 2)
            return new Ashbringer(0,0);
        else
            return null;
    }

    public Powerups getMediumLevelPowerup(){
        int random = (int) (Math.random() * 3);

        if(random == 0)
            return new HealthPotion(0,0,1);
        else if(random == 1)
            return new ManaPotion(0,0,1);
        else if(random == 2)
            return new JudgementArmor(0,0);
        else
            return null;
    }

    public Powerups getHighLevelPowerup(){
        int random = (int) (Math.random() * 3);

        if(random == 0)
            return new HealthPotion(0,0,2);
        else if(random == 1)
            return new ManaPotion(0,0,2);
        else if(random == 2)
            return new LightswornArmor(0,0);
        else
            return null;
    }
}
