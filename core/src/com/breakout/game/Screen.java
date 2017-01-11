package com.breakout.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Screen {
    protected final ScreenManager _screenManager;

    public Screen(ScreenManager screenManager) {
        _screenManager = screenManager;
    }

    abstract void update(float deltaTime);

    abstract void draw(SpriteBatch batch, ShapeRenderer shapeRenderer);
}
