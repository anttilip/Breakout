package com.breakout.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Breakout extends ApplicationAdapter {
    private SpriteBatch _batch;
    private ScreenManager _screenManager;

    @Override
    public void create () {
        _batch = new SpriteBatch();

        BitmapFont font = new BitmapFont();
        _screenManager = new ScreenManager(font);
        _screenManager.changeScreen(new GameScreen(_screenManager));

    }

    @Override
    public void render() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        update(deltaTime);
        draw();
    }

    private void update(float deltaTime) {
        _screenManager.updateScreen(deltaTime);
    }

    private void draw() {
        _batch.begin();
        _screenManager.drawScreen(_batch);
        _batch.end();
    }
}


/*
TODO:
- Better collision check with vectors (fixes high speed bugs and block kissing)
- Player Class with score and lives
- Color indicator for blocks entering kill zone
- Different blocks
- Drops?
- Screens
- Gdx.gl.glClearColor(25 / 255f, 113 / 255f, 146 / 255f, 1f);  Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); ->  GraphicsHelper.clear(255, 255, 255);

*/