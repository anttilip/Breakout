package com.breakout.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Breakout extends ApplicationAdapter {
	private SpriteBatch _batch;
	
	@Override
	public void create () {
        _batch = new SpriteBatch();
	}

	@Override
	public void render () {

	}

    private void update() {

    }

    private void draw() {
        _batch.begin();

        _batch.end();
    }
}
