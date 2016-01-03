package com.breakout.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScreenManager {
    private Screen _currentScreen;
    private BitmapFont _font;

    public ScreenManager(BitmapFont font) {
        _font = font;
    }

    public void changeScreen(Screen screen) {
        _currentScreen = screen;
    }

    public void updateScreen() {
        _currentScreen.update();
    }

    public void drawScreen(SpriteBatch batch) {
        _currentScreen.draw(batch);
    }
}
