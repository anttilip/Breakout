package com.breakout.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

public class BreakoutCamera extends GameObject {
    private static Vector2 _position = new Vector2(Constants.WORLD_SIZE.x / 2f,
            Constants.WORLD_SIZE.y / 2f);

    private OrthographicCamera _cam;

    public BreakoutCamera(EntityWorld entityWorld) {
        super(_position, entityWorld);

        Vector2 cameraSize = calculateCameraSize();
        _cam = new OrthographicCamera(cameraSize.x, cameraSize.y);

        _cam.position.set(_position.x, _position.y, 0);
        _cam.update();
    }

    private Vector2 calculateCameraSize() {
        float deviceAspectRatio = Constants.SCREEN_SIZE.x/Constants.SCREEN_SIZE.y;
        float worldAspectRatio = Constants.WORLD_SIZE.x/Constants.WORLD_SIZE.y;

        if(deviceAspectRatio > worldAspectRatio) {
            float multiplier = deviceAspectRatio - worldAspectRatio + 1;
            return new Vector2(Constants.WORLD_SIZE.x * multiplier, Constants.WORLD_SIZE.y);
        } else {
            float multiplier = worldAspectRatio - deviceAspectRatio + 1;
            return new Vector2(Constants.WORLD_SIZE.x, Constants.WORLD_SIZE.y * multiplier);
        }
    }


    public float xToWorld(float x) {
        return x * (Constants.WORLD_SIZE.x/ Constants.SCREEN_SIZE.x);
    }

    public float yToWorld(float y) {
        return y * (Constants.WORLD_SIZE.y/ Constants.SCREEN_SIZE.y);
    }

    public Matrix4 getMatrix() {
        return _cam.combined;
    }

    @Override
    void update(float deltaTime) {
        _cam.update();
    }

    @Override
    void draw(SpriteBatch batch) {

    }
}
