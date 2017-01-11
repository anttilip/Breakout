package com.breakout.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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
    void draw(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        batch.setProjectionMatrix(_entityWorld.get(BreakoutCamera.class).getMatrix());
        drawBackground(batch, shapeRenderer);
        this._entityWorld.draw(batch);

        if (!(this._entityWorld.getPlayer().isAlive() || this._entityWorld.get(BreakoutCamera.class).isCurrentlyShaking())) {
            // If player has no lives left and camera has stopped shaking, display GameOverScreen
               this._screenManager.changeScreen(new GameOverScreen(this._screenManager,
                    this._entityWorld.getPlayer().getScore()));

        }
    }

    private void drawBackground(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        Gdx.gl.glClearColor(25 / 255f, 113 / 255f, 146 / 255f, 1f);  // background color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.end();
        drawKillZone(shapeRenderer, Constants.KILLZONE_HEIGHT);
        batch.begin();
    }

    private void drawKillZone(ShapeRenderer shapeRenderer, float height) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setProjectionMatrix(_entityWorld.get(BreakoutCamera.class).getMatrix());

        for (int i = 0; i < Constants.WORLD_SIZE.x; i += 3) {
            shapeRenderer.line(i, height,  i + 2, height);
        }

        shapeRenderer.end();
    }
}
