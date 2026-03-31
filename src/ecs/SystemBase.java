package ecs;

import components.ComponentManager;

import java.awt.Graphics;

public abstract class SystemBase {

    protected EntityManager entityManager;
    protected ComponentManager componentManager;

    public SystemBase(EntityManager em, ComponentManager cm) {

        this.entityManager = em;
        this.componentManager = cm;
    }

    public void update() {}

    public void render(Graphics g) {}
}