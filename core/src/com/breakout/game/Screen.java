package com.breakout.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Screen {
    protected final ScreenManager _screenManager;

    public Screen(ScreenManager screenManager) {
        _screenManager = screenManager;
    }

    abstract void update();

    abstract void draw(SpriteBatch batch);

}
