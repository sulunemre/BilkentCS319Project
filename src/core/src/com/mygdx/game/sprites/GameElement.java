package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class GameElement {

    protected Vector2 position;
    protected Vector2 direction; // A unit vector that indicates the direction
    protected double speed; // Magnitude of the direction
    protected Texture elementTexture;
    protected Rectangle bounds;

//    public GameElement() {
//        direction = new Vector2(0,0);
//        //TODO: sil
//    }

    public GameElement(float x, float y, String texturePath){
        position = new Vector2(x, y);
        direction = new Vector2(1,0);
        elementTexture = new Texture(texturePath);
        bounds = new Rectangle(x, y, elementTexture.getWidth(), elementTexture.getHeight());
    }

    /**
     * Updates element location according to direction.
     * Also checks position bounds.
     * @param dt
     */
    public void update(float dt){
        Vector2 velocity = direction.scl((float) speed); // Transform unit vector to velocity
        position.mulAdd(velocity, dt*10); // Add velocity to position

        bounds.setPosition(position); // Update frame bounds accordingly
        direction.nor(); // Transform direction vector to unit vector

        // Check bounds
        if (position.y < 0)
            position.y = 0;
        if (position.y > 260) //TODO: sayılar düzenlenecek
            position.y = 260;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getDirection() {
        return direction;
    }

    public void setDirection(Vector2 direction) {
        this.direction = direction;
    }

    public Texture getElementTexture() {
        return elementTexture;
    }

    public void setElementTexture(Texture elementTexture) {
        this.elementTexture = elementTexture;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
