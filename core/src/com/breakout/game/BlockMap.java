package com.breakout.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class BlockMap {
    private static final int BlocksOnRow = 10;
    private static final int InitialBlocksOnColumn = 8;
    private static final float SecondsBetweenNewRows = 8;

    private final EntityWorld _entityWorld;
    private int _blockSize;
    private float _secondsSinceLastNewRow;

    public BlockMap(EntityWorld entityWorld) {
        _entityWorld = entityWorld;
        _secondsSinceLastNewRow = 0;
        _blockSize = (int)(_entityWorld.getScreenSize().x / (BlocksOnRow * 1.5 + 0.5));

        generateLayers(InitialBlocksOnColumn);
    }

    public void update(float deltaTime) {
        _secondsSinceLastNewRow += deltaTime;
        if(_secondsSinceLastNewRow >= SecondsBetweenNewRows) {
            addLayer();
            _secondsSinceLastNewRow = 0;
        }
    }

    private void addLayer() {
        // Move existing blocks down
        for(Block block : _entityWorld.getAll(Block.class)) {
            block.moveDown();
        }

        // Add new layer
        for(int i = 0; i < BlocksOnRow; i++) {
            _entityWorld.addGameObject(new Block(new Vector2(
                    _blockSize / 2 + (_blockSize * 1.5f) * i,
                    _entityWorld.getScreenSize().y - 2 * _blockSize),
                    new Vector2(_blockSize, _blockSize),
                    new Texture("Textures/racket.png"),
                    _entityWorld));
        }
    }

    private void generateLayers(int n) {
        for(int i = 0; i < n; i++) {
            addLayer();
        }
    }
}
