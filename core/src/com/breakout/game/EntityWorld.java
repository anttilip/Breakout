package com.breakout.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityWorld {
    private final Vector2 _screenSize;

    private final BlockMap _blockMap;
    private List<GameObject> _gameObjects;
    private Map<Class, List<GameObject>> _map;

    public EntityWorld(Vector2 screenSize) {
        _screenSize = screenSize;
        _gameObjects = new ArrayList<GameObject>();
        _map = new HashMap<Class, List<GameObject>>();
        _blockMap = new BlockMap(this);
    }

    public void update(float deltaTime) {
        removeDestroyed();

        for(GameObject gameObject : _gameObjects) {
            gameObject.update(deltaTime);
        }

        _blockMap.update(deltaTime);
    }

    public void draw(SpriteBatch batch) {
        for(GameObject gameObject : _gameObjects) {
            gameObject.draw(batch);
        }
    }

    public void addGameObject(GameObject gameObject) {
        _gameObjects.add(gameObject);
        if(!_map.containsKey(gameObject.getClass())) {
            _map.put(gameObject.getClass(), new ArrayList<GameObject>());
        }
        _map.get(gameObject.getClass()).add(gameObject);
    }

    public void removeDestroyed() {
        for(Class key : _map.keySet()) {
            List<GameObject> destroyed = new ArrayList<GameObject>();
            for(GameObject gameObject : _map.get(key)) {
                if(gameObject._isDestroyed) {
                    destroyed.add(gameObject);
                }
            }
            _map.get(key).removeAll(destroyed);
            _gameObjects.removeAll(destroyed);
        }
    }

    public <T> List<T> getAll(Class<T> type) {
        List<T> instances = new ArrayList<T>();
        for(Class<GameObject> c : _map.keySet()) {
            if(type.isAssignableFrom(c)) {
                instances.addAll((List<T>) _map.get(c));
            }
        }

        return instances;
    }

    public <T extends GameObject> T get(Class<T> type) {
        if(_map.get(type).size() == 1) {
            return (T) _map.get(type).get(0);
        }

        for(GameObject gameObject : _gameObjects) {
            if(type.isInstance(gameObject)) {
                return (T) gameObject;
            }
        }
        return null;
    }

    public Vector2 getScreenSize() {
        return _screenSize;
    }
}
