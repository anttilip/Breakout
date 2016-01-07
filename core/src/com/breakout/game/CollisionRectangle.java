package com.breakout.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class CollisionRectangle extends GameObject implements ICollidable {
    private Rectangle _rectangle;

    public CollisionRectangle(float x, float y, float width, float height, EntityWorld entityWorld) {
        super(new Vector2(x, y), new Vector2(width, height), null, entityWorld);
        _rectangle = new Rectangle(x, y, width, height);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return _rectangle;
    }

    @Override
    public Vector2 onCollision(Ball ball, Vector2 direction) {
        return direction;
    }

    @Override
    void update(float deltaTime) {

    }

    @Override
    void draw(SpriteBatch batch) {

    }
}
