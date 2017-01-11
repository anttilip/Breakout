package com.breakout.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

import static com.badlogic.gdx.math.MathUtils.random;

public class BreakoutCamera extends GameObject {
    private static Vector2 _position = new Vector2(Constants.WORLD_SIZE.x / 2f,
            Constants.WORLD_SIZE.y / 2f);

    private OrthographicCamera _cam;
    private float shakingElapsed;
    private float shakingDuration;


    public BreakoutCamera(EntityWorld entityWorld) {
        super(_position, entityWorld);

        Vector2 cameraSize = calculateCameraSize();
        _cam = new OrthographicCamera(cameraSize.x, cameraSize.y);

        _cam.position.set(_position.x, _position.y, 0);
        _cam.update();
        this.shakingElapsed = 0;
        this.shakingElapsed = 0;
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
        _cam.position.x = this._position.x;
        _cam.position.y = this._position.y;

        // Camera shake effect
        if (this.shakingElapsed < shakingDuration) {
            shake(deltaTime);
        }
        _cam.update();
    }

    @Override
    void draw(SpriteBatch batch) {

    }

    public void shakeCamera(float duration) {
        this.shakingElapsed = 0;
        this.shakingDuration = duration;
    }

    private void shake(float deltaTime) {
        float currentPower = 3 * _cam.zoom * ((shakingDuration- shakingElapsed) / shakingDuration);
        float x = (random.nextFloat() - 0.5f) * currentPower;
        float y = (random.nextFloat() - 0.5f) * currentPower;
        _cam.translate(-x, -y);

        // Increase the elapsed time by the delta provided.
        this.shakingElapsed += deltaTime;
    }


}
