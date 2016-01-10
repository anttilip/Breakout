package com.breakout.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {
    protected final EntityWorld _entityWorld;
    protected Vector2 _position;
    protected boolean _isDestroyed = false;

    public GameObject(Vector2 position, EntityWorld entityWorld) {
        _position = position;
        _entityWorld = entityWorld;
    }

    abstract void update(float deltaTime);

    abstract void draw(SpriteBatch batch);

    protected void destroy() {
        _isDestroyed = true;
    }
}
