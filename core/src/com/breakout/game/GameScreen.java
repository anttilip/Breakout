package com.breakout.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GameScreen extends Screen{
    private final EntityWorld _entityWorld;

    public GameScreen(ScreenManager screenManager) {
        super(screenManager);

        _entityWorld = new EntityWorld();

        _entityWorld.addGameObject(new BreakoutCamera(_entityWorld));

        _entityWorld.addGameObject(new Racket(new Vector2(45 - 10, 20),
                new Vector2(20, 2),
                new Texture("Textures/white_square.png"), _entityWorld));

        _entityWorld.addGameObject(new Ball(new Vector2(0, 0),
                new Vector2(5, 5),
                new Texture("Textures/ball.png"), _entityWorld));

        _entityWorld.addGameObject(new BlockMap(_entityWorld));

        // Make left wall
        _entityWorld.addGameObject(new CollisionRectangle(-Constants.WORLD_SIZE.x, 0,
                Constants.WORLD_SIZE.x, Constants.WORLD_SIZE.y, _entityWorld));
        // Make right wall
        _entityWorld.addGameObject(new CollisionRectangle(Constants.WORLD_SIZE.x, 0,
                Constants.WORLD_SIZE.x, Constants.WORLD_SIZE.y, _entityWorld));
        // Make top wall
        _entityWorld.addGameObject(new CollisionRectangle(-Constants.WORLD_SIZE.x,
                Constants.WORLD_SIZE.y, Constants.WORLD_SIZE.x * 3,
                Constants.WORLD_SIZE.y, _entityWorld));

    }

    @Override
    void update(float deltaTime) {
        _entityWorld.update(deltaTime);
    }

    @Override
    void draw(SpriteBatch batch) {
        if (this._entityWorld.getPlayer().isAlive()) {
            drawGameObjects(batch);
        } else {
            drawEndScreen(batch);
        }
        // Draw end screen
    }

    private void drawGameObjects(SpriteBatch batch) {

    }

    private void drawBackground(SpriteBatch batch) {
        batch.setProjectionMatrix(_entityWorld.get(BreakoutCamera.class).getMatrix());
        Gdx.gl.glClearColor(25 / 255f, 113 / 255f, 146 / 255f, 1f);  // background color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void drawEndScreen(SpriteBatch batch) {

    }
}
