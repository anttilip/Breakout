package com.breakout.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Ball extends GameObject {
    private Texture _texture;
    private Vector2 _direction;
    private Vector2 _size;
    private float _speed;
    private boolean _isOnRacket;

    public Ball(Vector2 position, Vector2 size, Texture texture, EntityWorld entityWorld) {
        super(position, entityWorld);
        _size = size;
        _texture = texture;
        _direction = new Vector2(0, 1);
        _speed = 80;
        _isOnRacket = true;
    }

    @Override
    void update(float deltaTime) {
        if(_isOnRacket) {
            onRacket();
            return;
        }

        //ball position update
        _position.x += _direction.x * _speed * deltaTime;
        _position.y += _direction.y * _speed * deltaTime;

        if(_position.y + this._size.y < 0) {
            onDeath();
        }

        checkCollisions();
    }

    @Override
    void draw(SpriteBatch batch) {
        batch.draw(_texture, _position.x, _position.y, _size.x, _size.y);
    }

    private void checkCollisions() {
        Rectangle ballRectangle = new Rectangle(_position.x, _position.y, _size.x, _size.y);
        for(ICollidable coll : _entityWorld.getAll(ICollidable.class)) {
            if(coll.getCollisionRectangle().overlaps(ballRectangle)) {
                if(coll instanceof GameObject) {
                    if(((GameObject) coll)._isDestroyed) {
                        continue;
                    }
                }

                Rectangle intersection = new Rectangle();
                Intersector.intersectRectangles(coll.getCollisionRectangle(),
                        ballRectangle, intersection);

                Vector2 newDirection = _direction;

                if(intersection.height > intersection.width) {
                    //right or left side
                    if (intersection.x > coll.getCollisionRectangle().x) {
                        //right
                        _position.x = coll.getCollisionRectangle().x +
                                coll.getCollisionRectangle().getWidth() + 1;
                        newDirection.x = Math.abs(_direction.x);
                    }
                    if (intersection.x + intersection.width <coll.getCollisionRectangle().x +
                            coll.getCollisionRectangle().width) {
                        //left
                        _position.x = coll.getCollisionRectangle().x - _size.x - 1;
                        newDirection.x = Math.abs(_direction.x) * -1;
                    }
                } else {
                    //top or bottom side
                    if (intersection.y > coll.getCollisionRectangle().y) {
                        //top
                        _position.y = coll.getCollisionRectangle().y +
                                coll.getCollisionRectangle().getHeight() + 1;
                        newDirection.y = Math.abs(_direction.y);
                    }
                    if (intersection.y + intersection.height < coll.getCollisionRectangle().y +
                            coll.getCollisionRectangle().height) {
                        //bottom
                        _position.y = coll.getCollisionRectangle().y - _size.y - 1;
                        newDirection.y = Math.abs(_direction.y) * -1;
                    }
                }

                _direction = coll.onCollision(this, newDirection);
            }
        }
    }

    private void onRacket() {
        Racket racket = _entityWorld.get(Racket.class);

        if(isLaunchAreaPressed()) {
            _isOnRacket = false;
        } else {
            _position.x = racket.getPosition().x + racket.getSize().x / 2 - _size.x / 2;
            _position.y = racket.getPosition().y + racket.getSize().y;
        }
    }

    private void onDeath() {
        _isOnRacket = true;
        _direction = new Vector2(0,1);
        _entityWorld.getPlayer().loseLife();
    }

    public Vector2 getSize() {
        return _size;
    }

    public Vector2 getPosition() {
        return _position;
    }

    public boolean isOnRacket() {
        return _isOnRacket;
    }

    private boolean isLaunchAreaPressed() {
        float touchPointInWorld = _entityWorld.get(BreakoutCamera.class).yToWorld(Gdx.input.getY());
        float racketPositionY = _entityWorld.get(Racket.class).getPosition().y;
        return Gdx.input.justTouched() && Constants.WORLD_SIZE.y - touchPointInWorld > racketPositionY;
    }
}
