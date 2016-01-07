package com.breakout.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;

public abstract class GameObject {
    protected final EntityWorld _entityWorld;
    protected Vector2 _position;
    protected Vector2 _size;
    protected Texture _texture;
    protected boolean _isDestroyed = false;

    public GameObject(Vector2 position, Vector2 size, Texture texture, EntityWorld entityWorld) {
        _position = position;
        _size = size;
        _texture = texture;
        _entityWorld = entityWorld;
    }

    abstract void update(float deltaTime);

    abstract void draw(SpriteBatch batch);

    protected void destroy() {
        _isDestroyed = true;
    }

    public Rectangle getRectangle() {
        return new Rectangle(_position.x, _position.y, _size.x, _size.y);
    }

}
