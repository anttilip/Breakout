package com.breakout.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends Screen{
    private EntityWorld _entityWorld;

    public GameScreen(ScreenManager screenManager) {
        super(screenManager);
        _entityWorld =  new EntityWorld();
    }

    @Override
    void update() {
        _entityWorld.update();
    }

    @Override
    void draw(SpriteBatch batch) {
        Gdx.gl.glClearColor(25 / 255f, 113 / 255f, 146 / 255f, 1f);  // background color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        _entityWorld.draw(batch);
    }
}
