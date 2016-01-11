package com.breakout.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class BlockMap extends GameObject{
    private static final int BLOCKS_ON_ROW = 10;
    private static final int INITIAL_BLOCKS_ON_COLUMN = 8;
    private static final float SECONDS_BETWEEN_NEW_ROWS = 8;
    private static final float SIZE = Constants.WORLD_SIZE.x / (BLOCKS_ON_ROW * 1.5f + 0.5f);

    private final EntityWorld _entityWorld;
    private float _secondsSinceLastNewRow;

    public BlockMap(EntityWorld entityWorld) {
        super(Vector2.Zero, entityWorld);
        _entityWorld = entityWorld;
        _secondsSinceLastNewRow = 0;

        generateInitialRows();
    }

    public void update(float deltaTime) {
        if(_entityWorld.get(Ball.class).isOnRacket()) {
            return;
        }

        _secondsSinceLastNewRow += deltaTime;
        if(_secondsSinceLastNewRow >= SECONDS_BETWEEN_NEW_ROWS) {
            addNewRow();
            _secondsSinceLastNewRow = 0;
        }
    }

    @Override
    void draw(SpriteBatch batch) {

    }

    private void addNewRow() {
        // Move existing blocks down
        for(Block block : _entityWorld.getAll(Block.class)) {
            block.moveDown();
        }

        // Add new row
        for(int i = 0; i < BLOCKS_ON_ROW; i++) {
            _entityWorld.addGameObject(new Block(new Vector2(SIZE / 2 + (SIZE * 1.5f) * i,
                        Constants.WORLD_SIZE.y - 2 * SIZE),
                    new Vector2(SIZE, SIZE),
                    new Texture("Textures/white_square.png"),
                    _entityWorld));
        }
    }

    private void generateInitialRows() {
        for(int i = 0; i < INITIAL_BLOCKS_ON_COLUMN; i++) {
            addNewRow();
        }
    }
}
