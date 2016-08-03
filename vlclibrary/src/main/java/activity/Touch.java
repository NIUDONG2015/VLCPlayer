package activity;

/**
 * Created by dys on 2016/8/2 0002.
 */
public class Touch {
    public int leftTopX;
    public int leftTopY;
    public int rightBottomX;
    public int rightBottomY;
    public int pointX;
    public int pointY;

    public Touch(int leftTopX, int leftTopY, int rightBottomX, int rightBottomY, int pointX, int pointY) {
        this.leftTopX = leftTopX;
        this.leftTopY = leftTopY;
        this.rightBottomX = rightBottomX;
        this.rightBottomY = rightBottomY;
        this.pointX = pointX;
        this.pointY = pointY;
    }
}
