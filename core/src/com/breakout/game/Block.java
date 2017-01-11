package com.breakout.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Block extends GameObject implements ICollidable {
    private Vector2 _size;
    private Texture _texture;
    private int _durability;
    private float _gapSize;

    public Block(Vector2 position, Vector2 size, Texture texture, EntityWorld entityWorld) {
        super(position, entityWorld);
        _size = size;
        _texture = texture;
        _durability = 1;
        _gapSize = size.x * 0.5f;
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
        if(_position.y <= Constants.KILLZONE_HEIGHT) {
            destroy();
            this._entityWorld.getPlayer().loseLife();
        }
    }

    public void destroy() {
        _isDestroyed = true;
        this._entityWorld.getPlayer().increaseScore(this._durability * 100);
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