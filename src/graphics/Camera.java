package graphics;

public class Camera {

    public double x;
    public double y;

    public int viewportWidth;
    public int viewportHeight;

    public int worldWidth;
    public int worldHeight;


    public Camera(int width, int height) {

        this.viewportWidth = width;
        this.viewportHeight = height;
    }

    public void setWorldSize(int width, int height) {
        this.worldWidth = width;
        this.worldHeight = height;
    }

}