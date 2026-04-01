package graphics;

public class CameraManager {

    private static Camera camera;

    public static void init(int width, int height) {

        camera = new Camera(width, height);
    }

    public static Camera getCamera() {

        return camera;
    }

    public static void clampToWorld() {

        if(camera.x < 0) camera.x = 0;
        if(camera.y < 0) camera.y = 0;

        if(camera.x > camera.worldWidth - camera.viewportWidth)
            camera.x = camera.worldWidth - camera.viewportWidth;

        if(camera.y > camera.worldHeight - camera.viewportHeight)
            camera.y = camera.worldHeight - camera.viewportHeight;
    }
}