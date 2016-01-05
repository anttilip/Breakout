package com.breakout.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Screen {
    protected final ScreenManager _screenManager;
    protected final Vector2 _screenSize;

    public Screen(ScreenManager screenManager) {
        _screenManager = screenManager;
        _screenSize = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    abstract void update(float deltaTime);

    abstract void draw(SpriteBatch batch);
}
