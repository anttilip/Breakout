package com.breakout.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityWorld {
    private final List<GameObject> _gameObjects;
    private final Map<Class, List<GameObject>> _map;

    private List<GameObject> _gameObjectsToAdd;
    private boolean _currentlyInUpdate;

    public EntityWorld() {
        _gameObjects = new ArrayList<GameObject>();
        _gameObjectsToAdd = new ArrayList<GameObject>();
        _map = new HashMap<Class, List<GameObject>>();
        _currentlyInUpdate = false;
    }

    public void update(float deltaTime) {
        removeDestroyed();
        _currentlyInUpdate = true;

        for(GameObject gameObject : _gameObjects) {
            gameObject.update(deltaTime);
        }

        _currentlyInUpdate = false;

        // Add created GameObjects to _gameObjects
        for(GameObject gameObject : _gameObjectsToAdd) {
            addGameObjectInternal(gameObject);
        }
        _gameObjectsToAdd.clear();
    }

    public void draw(SpriteBatch batch) {
        for(GameObject gameObject : _gameObjects) {
            gameObject.draw(batch);
        }
    }

    public void addGameObject(GameObject gameObject) {
        if(_currentlyInUpdate) {
            _gameObjectsToAdd.add(gameObject);
        } else {
            addGameObjectInternal(gameObject);
        }
    }

    private void addGameObjectInternal(GameObject gameObject) {
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
                    gameObject.destroy();
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
        // If game object is unique, return it
        if(_map.containsKey(type)) {
            return (T) _map.get(type).get(0);
        }

        return null;
    }
}
