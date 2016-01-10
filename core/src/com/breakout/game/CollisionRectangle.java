package com.breakout.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class CollisionRectangle extends GameObject implements ICollidable {
    private final Rectangle _rectangle;
    private Texture _texture;

    public CollisionRectangle(float x, float y, float width, float height, EntityWorld entityWorld) {
        super(new Vector2(x, y), entityWorld);
        _rectangle = new Rectangle(x, y, width, height);
        _texture = new Texture("Textures/black_square.png");
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
        batch.draw(_texture, _position.x, _position.y, _rectangle.width, _rectangle.height);
    }
}
