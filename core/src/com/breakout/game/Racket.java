package com.breakout.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Racket extends GameObject implements ICollidable {
    private Vector2 _size;
    private Texture _texture;

    public Racket(Vector2 position, Vector2 size, Texture texture, EntityWorld entityWorld) {
        super(position, entityWorld);
        _size = size;
        _texture = texture;
    }

    @Override
    void update(float deltaTime) {
        if(Gdx.input.isTouched()) {
            _position.x = _entityWorld.get(BreakoutCamera.class).xToWorld(Gdx.input.getX()) - _size.x/2;

            // Racket can't go out of bounds
            if(_position.x < 0) {
                _position.x = 0;
            } else if(_position.x + _size.x > Constants.WORLD_SIZE.x) {
                _position.x = Constants.WORLD_SIZE.x - _size.x;
            }
        }
    }

    @Override
    void draw(SpriteBatch batch) {
        batch.draw(_texture, _position.x, _position.y, _size.x, _size.y);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return new Rectangle(_position.x, _position.y, _size.x, _size.y);
    }

    @Override
    public Vector2 onCollision(Ball ball, Vector2 direction) {
        // This changes balls direction based on where on racket the ball hits
        direction.x = (ball.getPosition().x + (ball.getSize().x / 2) - _position.x) / (_size.x) * 2 - 1;
        direction.nor();

        // Don't allow too horizontal directions
        if(direction.x > 0.75) {
            direction.x = 0.75f;
            direction.nor();
        } else if(direction.x < -0.75f) {
            direction.x = -0.75f;
            direction.nor();
        }

        return direction;
    }

    public Vector2 getSize() {
        return _size;
    }

    public Vector2 getPosition() {
        return _position;
    }
}
