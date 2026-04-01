package graphics;

public class CameraManager {

    private static Camera camera;

    public static void init(int width, int height) {

        camera = new Camera(width, height);
    }

    public static Camera getCamera() {

        return camera;
    }

}