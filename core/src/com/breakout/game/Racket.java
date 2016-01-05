package com.breakout.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Racket extends GameObject {
    public Racket(Vector2 position, Vector2 size, Texture texture, EntityWorld entityWorld) {
        super(position, size, texture, entityWorld);
    }

    @Override
    void update(float deltaTime) {
        if(Gdx.input.isTouched()) {
            _position.x = Gdx.input.getX() - _size.x/2;

            // Racket can't go out of bounds
            if(_position.x < 0) {
                _position.x = 0;
            } else if(_position.x + _size.x > _entityWorld.getScreenSize().x) {
                _position.x = _entityWorld.getScreenSize().x - _size.x;
            }
        }
    }

    @Override
    void draw(SpriteBatch batch) {
        batch.draw(_texture, _position.x, _position.y, _size.x, _size.y);
    }
}
