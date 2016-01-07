package com.breakout.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public interface ICollidable {

    Rectangle getCollisionRectangle();

    Vector2 onCollision(Ball ball, Vector2 direction);
}
