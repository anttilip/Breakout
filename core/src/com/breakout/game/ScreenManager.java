package com.breakout.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class ScreenManager {
    private final BitmapFont _font;
    private Screen _currentScreen;

    public ScreenManager(BitmapFont font) {
        _font = font;
    }

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
