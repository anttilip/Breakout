package com.breakout.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Racket extends GameObject {
    public Racket(Vector2 position, Vector2 size, Texture texture, EntityWorld entityWorld) {
        super(position, size, texture, entityWorld);
    }

    @Override
    void update() {

    }

    @Override
    void draw(SpriteBatch batch) {
        batch.draw(_texture, _position.x, _position.y, _size.x, _size.y);
    }
}
