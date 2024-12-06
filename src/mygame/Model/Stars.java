package mygame.Model;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Stars {
    private Image image;
    private int x, y;
    private int height, width;
    private boolean isVisible;

    //    private static final int WIDTH = 938;
    private static int SPEED = 4;

    public Stars(int x, int y) {
        this.x = x;
        this.y = y;
        isVisible = true;
    }

    public void load() {
        ImageIcon reference = new ImageIcon("src/res/star.png");
        image = reference.getImage();

        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
    }

    public void update() {
        if (this.x < 0) {
            this.x = width;
            Random a = new Random();
            int m = a.nextInt(500);
            this.x = m + 1024;
            Random r = new Random();
            int n = r.nextInt(768);
            this.y = n;
        }else{
            this.x -= SPEED;
        }
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public static int getSPEED() {
        return SPEED;
    }

    public static void setSPEED(int SPEED) {
        Stars.SPEED = SPEED;
    }
}
