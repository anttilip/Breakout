package com.breakout.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Ball extends GameObject {
    private Vector2 _direction;
    private float _speed;
    private boolean _isOnRacket;

    public Ball(Vector2 position, Vector2 size, Texture texture, EntityWorld entityWorld) {
        super(position, size, texture, entityWorld);
        _direction = new Vector2(1,1);
        _speed = 10;
        _isOnRacket = true;
    }

    @Override
    void update() {
        if(_isOnRacket) {
            onRacket();
            return;
        }

        //ball position update
        _position.x += _direction.x * _speed;
        _position.y += _direction.y * _speed;

        checkCollisions();
    }

    @Override
    void draw(SpriteBatch batch) {
        batch.draw(_texture, _position.x, _position.y, _size.x, _size.y);
    }

    private void checkCollisions() {
        // collision on wall
        checkCollisionAgainstWall(_entityWorld.getScreenSize());

        // collision on racket
        if(checkCollision(_entityWorld.getRacket())) {
            onRacketCollision(_entityWorld.getRacket());
        }
    }

    private void checkCollisionAgainstWall(Vector2 screenSize) {
        if(_position.y + _size.y > screenSize.y) {
            _position.y = screenSize.y - _size.y;
            _direction.y *= -1;
        } else if(_position.x < 0) {
            _position.x = 0;
            _direction.x *= -1;
        } else if (_position.x + _size.x > screenSize.x) {
            _position.x = screenSize.x - _size.x;
            _direction.x *= -1;
        } else if(_position.y < 0) {
            onDeath();
        }
    }

    private boolean checkCollision(GameObject object) {
        return object.getRectangle().overlaps(this.getRectangle());
    }

    private void onRacketCollision(Racket racket) {
        _direction.y = Math.abs(_direction.y);
        _position.y = racket._position.y + racket._size.y;

        // This changes balls direction based on where on racket the ball hits
        _direction.x = (_position.x + (_size.x / 2) - racket._position.x) / (racket._size.x) * 2 - 1;
        _direction.nor();
    }

    public boolean isOnRacket() {
        return _isOnRacket;
    }

    private void onRacket() {
        Racket racket = _entityWorld.getRacket();

        if(Gdx.input.justTouched() && _entityWorld.getScreenSize().y - Gdx.input.getY() > racket._position.y) {
            _isOnRacket = false;
        } else {
            _position.x = racket._position.x + racket._size.x / 2 - _size.x / 2;
            _position.y = racket._position.y + racket._size.y;
        }
    }

    private void onDeath() {
        _isOnRacket = true;
        _direction = new Vector2(1,1);
    }
}
