package com.breakout.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Align;


public class GameOverScreen extends Screen {
    private GlyphLayout layout;
    private BitmapFont bigFont;
    private BitmapFont mediumFont;
    private BitmapFont smallFont;
    private String bigText;
    private String smallText;
    private String infoText;
    private Matrix4 normalProjection;
    private int inputDelay;
    private long initTime;

    public GameOverScreen(ScreenManager screenManager, int finalScore) {
        super(screenManager);
        this.smallFont = Fonts.getSmallFont();
        this.mediumFont = Fonts.getMediumFont();
        this.bigFont = Fonts.getBigFont();

        this.bigText = "GAME\nOVER";
        this.smallText = "Your final score\nwas: " + finalScore;
        this.infoText = "Tap anywhere to try again";
        this.layout = new GlyphLayout();
        this.inputDelay = 1000; // One second delay
        this.initTime = System.currentTimeMillis();

        this.normalProjection = new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
    }

    @Override
    void update(float deltaTime) {
        long millisSinceInit = System.currentTimeMillis() - this.initTime;
        if (millisSinceInit > this.inputDelay && Gdx.input.justTouched()) {
            super._screenManager.changeScreen(new GameScreen(this._screenManager));
        }
    }

    @Override
    void draw(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        Gdx.gl.glClearColor(25 / 255f, 113 / 255f, 146 / 255f, 1f);  // background color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(this.normalProjection);

        // Draw game over text
        int y = 100;
        layout.setText(bigFont, bigText, Color.WHITE, Gdx.graphics.getWidth(), Align.center, true);
        bigFont.draw(batch, layout, 0, Gdx.graphics.getHeight() - y);

        // Draw score text
        y += layout.height;
        layout.setText(mediumFont, smallText, Color.WHITE, Gdx.graphics.getWidth(), Align.center, true);
        mediumFont.draw(batch, layout, 0, Gdx.graphics.getHeight() * 0.8f - y);

        // Draw try again text
        layout.setText(smallFont, infoText, Color.WHITE, Gdx.graphics.getWidth(), Align.center, true);
        smallFont.draw(batch, layout, 0 , Gdx.graphics.getHeight() * 0.1f);
    }
}
