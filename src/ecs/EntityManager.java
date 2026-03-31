package ecs;

public class EntityManager {

    private int nextId = 0;

    public int createEntity() {

        return nextId++;
    }
}