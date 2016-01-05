package com.breakout.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class BlockMap {
    private final EntityWorld _entityWorld;
    private List<Block> _blocks;
    private int _blocksOnRow;
    private int _initialBlocksOnColumn;
    private int _blockSize;
    private float _secondsSinceLastNewRow;
    private float _secondsToNewRow;

    public BlockMap(EntityWorld entityWorld) {
        _entityWorld = entityWorld;
        _blocks = new ArrayList<Block>();

        _secondsToNewRow = 8;
        _secondsSinceLastNewRow = 0;
        _blocksOnRow = 10;
        _initialBlocksOnColumn = 8;
        _blockSize = (int)(_entityWorld.getScreenSize().x / (_blocksOnRow * 1.5 + 0.5));
        generateLayers(_initialBlocksOnColumn);
    }

    private void addLayer() {
        // Move existing blocks down
        for(Block block : _blocks) {
            block.moveDown();
        }

        // Add new layer
        for(int i = 0; i < _blocksOnRow; i++) {
            _blocks.add(new Block(new Vector2(
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

    public void update(float deltaTime) {
        _secondsSinceLastNewRow += deltaTime;
        if(_secondsSinceLastNewRow >= _secondsToNewRow) {
            removeDestroyedBlocks();
            addLayer();
            _secondsSinceLastNewRow = 0;
        }
    }

    public void draw(SpriteBatch batch) {
        for(Block block : _blocks) {
            block.draw(batch);
        }
    }

    public List<Block> getBlocks() {
        return _blocks;
    }

    private void removeDestroyedBlocks() {
        List<Block> destroyedBlocks = new ArrayList<Block>();
        for(Block block : _blocks) {
            if(!block.isAlive()) {
                destroyedBlocks.add(block);
            }
        }
        _blocks.removeAll(destroyedBlocks);
    }
}
