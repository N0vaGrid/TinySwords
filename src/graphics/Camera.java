package graphics;

public class Camera {

    public double x;
    public double y;

    public int viewportWidth;
    public int viewportHeight;

    public Camera(int width, int height) {

        this.viewportWidth = width;
        this.viewportHeight = height;
    }

}