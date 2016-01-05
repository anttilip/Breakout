package com.breakout.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class EntityWorld {
    private final Ball _ball;
    private final Racket _racket;
    private final BlockMap _blockMap;
    private final Vector2 _screenSize;

    public EntityWorld(Vector2 screenSize) {
        _screenSize = screenSize;
        _racket = new Racket(new Vector2(_screenSize.x/2 - 128, _screenSize.y/10 + 64),
                             new Vector2(_screenSize.x/5, _screenSize.y/68),
                             new Texture("Textures/racket.png"), this);

        _ball = new Ball(new Vector2(_racket._position),
                         new Vector2(_screenSize.x/20, _screenSize.x/20),
                         new Texture("Textures/ball.png"), this);

        _blockMap = new BlockMap(this);
    }

    public void update(float deltaTime) {
        _ball.update(deltaTime);
        _racket.update(deltaTime);
        _blockMap.update(deltaTime);
    }

    public void draw(SpriteBatch batch) {
        _ball.draw(batch);
        _racket.draw(batch);
        _blockMap.draw(batch);
    }

    public Ball getBall() {
        return _ball;
    }

    public Racket getRacket() {
        return _racket;
    }

    public BlockMap getBlockMap() {
        return _blockMap;
    }

    public Vector2 getScreenSize() {
        return _screenSize;
    }
}
