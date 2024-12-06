package mygame.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Player implements ActionListener {
    private int x, y;
    private int dx, dy;
    private Image image;
    private int height, width;
    private List<Shot> shots;
    private boolean isVisible, isTurbo;
    private Timer timerP;

    public Player() {
        this.x = 100;
        this.y = 100;
        isVisible = true;
        isTurbo = false;
        // estamos definindo onde o player vai spawnar

        shots = new ArrayList<Shot>();
        timerP = new Timer(5000, this);
        timerP.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isTurbo) {
            turbo();
            isTurbo = false;
        }

        if (!isTurbo) {
            load();
        }
    }

    public void load() {
        //definir imagem do player
        String path = "src/res/spaceShip.png";
        ImageIcon reference = new ImageIcon(path);
        image = reference.getImage();

        height = image.getHeight(null);
        width = image.getWidth(null);
    }

    public void update() {
        x += dx;
        y += dy;
        //fazendo a movimentação
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void simpleShot() {
        this.shots.add(new Shot(x + width, y));
    }

    public void turbo() {
        isTurbo = true;
        ImageIcon reference = new ImageIcon("src/res/spaceShipTurbo.png");
        image = reference.getImage();
    }


    public void keyPressed(KeyEvent key) {
        int code = key.getKeyCode();

        switch (code) {
            case KeyEvent.VK_SPACE:
                if (!isTurbo) {
                    simpleShot();
                }
                break;
            case KeyEvent.VK_SHIFT:
                turbo();
                break;
            case KeyEvent.VK_W:
                dy = -6;
                break;
            case KeyEvent.VK_S:
                dy = 6;
                break;
            case KeyEvent.VK_A:
                dx = -6;
                break;
            case KeyEvent.VK_D:
                dx = 6;
                break;
        }
    }

    public void keyRelease(KeyEvent key) {
        int code = key.getKeyCode();

        switch (code) {
            case KeyEvent.VK_W:
                dy = 0;
                break;
            case KeyEvent.VK_S:
                dy = 0;
                break;
            case KeyEvent.VK_A:
                dx = 0;
                break;
            case KeyEvent.VK_D:
                dx = 0;
                break;
        }
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
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

    public List<Shot> getShots() {
        return shots;
    }

    public boolean isTurbo() {
        return isTurbo;
    }

    public void setTurbo(boolean turbo) {
        isTurbo = turbo;
    }
}
