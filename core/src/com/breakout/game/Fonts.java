package com.breakout.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public final class Fonts {
    private static BitmapFont scoreFont;
    private static BitmapFont bigFont;
    private static BitmapFont mediumFont;
    private static BitmapFont smallFont;

    static {
        FileHandle fontFile = Gdx.files.internal("game_over.ttf");
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = (int) (50 * Gdx.graphics.getDensity());
        scoreFont = generator.generateFont(parameter);

        parameter.size = (int) (40 * Gdx.graphics.getDensity());
        smallFont = generator.generateFont(parameter);

        parameter.size = (int) (100 * Gdx.graphics.getDensity());
        mediumFont = generator.generateFont(parameter);

        parameter.size = (int) (200 * Gdx.graphics.getDensity());
        bigFont = generator.generateFont(parameter);

        generator.dispose();
    }

    public static BitmapFont getScoreFont() {
        return scoreFont;
    }

    public static BitmapFont getSmallFont() {
        return smallFont;
    }

    public static BitmapFont getMediumFont() {
        return mediumFont;
    }

    public static BitmapFont getBigFont() {
        return bigFont;
    }


}
