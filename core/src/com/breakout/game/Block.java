package com.breakout.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Block extends GameObject implements ICollidable {
    private int _durability;
    private int _gapSize;

    public Block(Vector2 position, Vector2 size, Texture texture, EntityWorld entityWorld) {
        super(position, size, texture, entityWorld);
        _durability = 1;
        _gapSize = (int) (size.x * 0.5f);
    }

    @Override
    void update(float deltaTime) {

    }

    @Override
    void draw(SpriteBatch batch) {
        if (!_isDestroyed) {
            batch.draw(_texture, _position.x, _position.y, _size.x, _size.y);
        }
    }

    public void moveDown() {
        _position.y -= _size.y + _gapSize;
    }

    public void destroy() {
        _isDestroyed = true;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return new Rectangle(_position.x, _position.y, _size.x, _size.y);
    }

    @Override
    public Vector2 onCollision(Ball ball, Vector2 direction) {
        if (!_isDestroyed) destroy();
        return direction;
    }
}