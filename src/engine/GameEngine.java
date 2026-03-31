package engine;

import scene.SceneManager;
import scene.GameScene;

import java.awt.Graphics;

public class GameEngine {

    private SceneManager sceneManager;

    public GameEngine() {

        sceneManager = new SceneManager();

        sceneManager.setScene(new GameScene());
    }

    public void update() {

        sceneManager.update();
    }

    public void render(Graphics g) {

        sceneManager.render(g);
    }
}