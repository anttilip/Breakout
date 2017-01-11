package com.breakout.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class ScreenManager {
    private Screen _currentScreen;

    public ScreenManager() {}

    public void changeScreen(Screen screen) {
        _currentScreen = screen;
    }

    public void updateScreen(float deltaTime) {
        _currentScreen.update(deltaTime);
    }

    public void drawScreen(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        _currentScreen.draw(batch, shapeRenderer);
    }
}
