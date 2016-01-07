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
        _entityWorld =  new EntityWorld(_screenSize);
        _entityWorld.addGameObject(new Racket(new Vector2(_screenSize.x / 2 - 128, _screenSize.y / 8),
                                              new Vector2(_screenSize.x / 5, _screenSize.y / 68),
                                              new Texture("Textures/racket.png"), _entityWorld));

        _entityWorld.addGameObject(new Ball(new Vector2(_entityWorld.get(Racket.class)._position),
                                            new Vector2(_screenSize.x / 20, _screenSize.x / 20),
                                            new Texture("Textures/ball.png"), _entityWorld));

        // Make walls
        _entityWorld.addGameObject(new CollisionRectangle(-_screenSize.x, 0, _screenSize.x,
                                                         _screenSize.y, _entityWorld));

        _entityWorld.addGameObject(new CollisionRectangle(_screenSize.x, 0, _screenSize.x,
                                                         _screenSize.y, _entityWorld));

        _entityWorld.addGameObject(new CollisionRectangle(-_screenSize.x, _screenSize.y,
                                                         _screenSize.x * 3, _screenSize.x, _entityWorld));

    }

    @Override
    void update(float deltaTime) {
        _entityWorld.update(deltaTime);
    }

    @Override
    void draw(SpriteBatch batch) {
        Gdx.gl.glClearColor(25 / 255f, 113 / 255f, 146 / 255f, 1f);  // background color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        _entityWorld.draw(batch);
    }
}
