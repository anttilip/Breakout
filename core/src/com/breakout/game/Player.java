package com.breakout.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

public class Player {
    private EntityWorld entityWorld;
    private Texture lifeTexture;
    private String scoreString;
    private BitmapFont font;
    private Matrix4 fontProjMatrix;

    private int score;
    private int lives;
    private boolean alive;


    public Player(EntityWorld entityWorld) {
        this.entityWorld = entityWorld;
        this.font = Fonts.getScoreFont();
        this.alive = true;
        this.lifeTexture = new Texture("Textures/small_red_heart.png");
        this.fontProjMatrix = new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(),
                                                               Gdx.graphics.getHeight());
        this.score = 0;
        this.lives = 3;
        this.scoreString = "0";
    }

    public boolean isAlive() {
        return this.alive;
    }

    public void increaseScore(int score) {
        this.score += score;
        this.scoreString = Integer.toString(this.score);
    }

    public int getScore() {
        return this.score;
    }

    public void loseLife() {
        this.lives--;
        if (this.lives == 0) {
            this.alive = false;
        }
        // When life is lost, shake the camera
        this.entityWorld.get(BreakoutCamera.class).shakeCamera(0.2f);
    }

    public void gainLife() {
        this.lives++;
    }

    public void draw(SpriteBatch batch) {
        drawScore(batch);
        drawLives(batch);

    }

    private void drawLives(SpriteBatch batch) {
        int offset = 0;
        for (int i = 1; i <= this.lives; i++) {
            float xPosition = Constants.WORLD_SIZE.x - this.lifeTexture.getWidth() * i - offset;
            float yPosition = Constants.WORLD_SIZE.y - 5;
            batch.draw(this.lifeTexture, xPosition, yPosition, 5, 5);
        }
    }

    private void drawScore(SpriteBatch batch) {
        batch.setProjectionMatrix(fontProjMatrix);
        this.font.draw(batch, this.scoreString, 10,  Constants.SCREEN_SIZE.y - 10);
        batch.setProjectionMatrix(entityWorld.get(BreakoutCamera.class).getMatrix());
    }
}
