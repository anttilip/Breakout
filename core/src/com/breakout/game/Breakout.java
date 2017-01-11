package com.breakout.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Breakout extends ApplicationAdapter {
    private SpriteBatch _batch;
    private ShapeRenderer _shapeRenderer;
    private ScreenManager _screenManager;

    @Override
    public void create () {
        _batch = new SpriteBatch();
        _shapeRenderer = new ShapeRenderer();

        _screenManager = new ScreenManager();
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
        _screenManager.drawScreen(_batch, _shapeRenderer);
        _batch.end();
    }
}

