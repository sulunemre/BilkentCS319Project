package com.mygdx.game.sprites.enemies;

public class EnemyFactory {
    public Enemy getEnemy(String enemyType, float x ,  float y){
        if(enemyType == null){
            return null;
        }

        if(enemyType.equalsIgnoreCase("ABOMINATION")){
            return new Abomination(x, y);
        } else if(enemyType.equalsIgnoreCase("GARGOYLE")){
            return new Gargoyle(x, y);
        } else if(enemyType.equalsIgnoreCase("GRUNT")){
            return new Grunt(x, y);
        } else if(enemyType.equalsIgnoreCase("SKELETON_WARRIOR")){
            return new SkeletonWarrior(x, y);
        } else if(enemyType.equalsIgnoreCase("BOSS")){
            return new Boss(x, y);

        } else {
            return null;
        }
    }
}
