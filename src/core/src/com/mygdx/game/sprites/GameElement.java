package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class GameElement {

    protected Vector2 position;
    protected Vector2 velocity;
    protected Texture elementTexture;
    protected Rectangle bounds;

    public GameElement() {
        velocity = new Vector2(0,0);
        //TODO: sil
    }

    /**
     * Updates element location according to velocity.
     * Also checks position bounds.
     */
    public void update()
    {
        position.add(velocity);
        bounds.setPosition(position);
        if (position.y < 0)
            position.y = 0;
        if (position.y > 260) //TODO: sayılar düzenlenecek
            position.y = 260;
        if (position.x < 0)
            position.x = 0;
        if (position.x > 500)
            position.x = 500;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
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
}
