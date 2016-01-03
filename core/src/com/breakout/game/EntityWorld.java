package com.breakout.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class EntityWorld {
    private Ball _ball;
    private Racket _racket;
    private Vector2 _screenSize;

    public EntityWorld() {
        _screenSize = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        _racket = new Racket(new Vector2(_screenSize.x/2 - 128, _screenSize.y/10 + 128),
                             new Vector2(_screenSize.x/5, _screenSize.y/68),
                             new Texture("Textures/racket.png"), this);

        _ball = new Ball(new Vector2(_racket._position),
                         new Vector2(_screenSize.x/20, _screenSize.x/20),
                         new Texture("Textures/ball.png"), this);
    }

    public void update() {
        _ball.update();
        _racket.update();
    }

    public void draw(SpriteBatch batch) {
        _ball.draw(batch);
        _racket.draw(batch);
    }

    public Ball getBall() {
        return _ball;
    }

    public Racket getRacket() {
        return _racket;
    }
}
