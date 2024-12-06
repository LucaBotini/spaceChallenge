package mygame.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Phase extends JPanel implements ActionListener {
    private Image bottom;
    private Player player;
    private Timer timer;
    private List<Enemy1> enemy1;
    private List<Stars> stars;
    private boolean inGame;


    public Phase() {
        setFocusable(true);
        setDoubleBuffered(true);
        String path = "src\\res\\background.png";
        ImageIcon reference = new ImageIcon(path); //recebe a imagem
        bottom = reference.getImage(); // variavel de referencia para imagem

        player = new Player();
        player.load();

        addKeyListener(new keyboardAdapter());

        timer = new Timer(5, this); //speed for game
        timer.start();
        initEnemy();
        initStars();
        inGame = true;
    }

    public void initEnemy() {
        int coords[] = new int[40]; //inimigos começa com 40
        enemy1 = new ArrayList<Enemy1>();
        for (int i = 0; i < coords.length; i++) {
            int x = (int) (Math.random() * 8000 + 1024); //aparecer o inimigo aleatorio na tela;
            int y = (int) (Math.random() * 650 + 30); //aparecer o inimigo aleatorio na tela;
            enemy1.add(new Enemy1(x, y));
        }
    }

    public void initStars() {
        int coords[] = new int[500];
        stars = new ArrayList<Stars>();
        for (int i = 0; i < coords.length; i++) {
            int x = (int) (Math.random() * 1024 + 0); //aparecer o inimigo aleatorio na tela;
            int y = (int) (Math.random() * 768 + 0); //aparecer o inimigo aleatorio na tela;
            stars.add(new Stars(x, y));
        }
    }

    public void paint(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        if (inGame) {
            graphics.drawImage(bottom, 0, 0, null);//colocando a imagem de fato na janela
            for (int p = 0; p < stars.size(); p++) {
                Stars q = stars.get(p);
                q.load();
                graphics.drawImage(q.getImage(), q.getX(), q.getY(), this);
            }
            graphics.drawImage(player.getImage(), player.getX(), player.getY(), this);
            List<Shot> shots = player.getShots();
            for (int i = 0; i < shots.size(); i++) {
                Shot m = shots.get(i);
                m.load();
                graphics.drawImage(m.getImage(), m.getX(), m.getY(), this);
            }

            for (int j = 0; j < enemy1.size(); j++) {
                Enemy1 in = enemy1.get(j);
                in.load();
                graphics.drawImage(in.getImage(), in.getX(), in.getY(), this);
            }
        } else {
            ImageIcon endGame = new ImageIcon("src/res/gameOver.png");
            graphics.drawImage(endGame.getImage(), 0, 0, null);
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.update();
        if (player.isTurbo()) {
            timer.setDelay(1);
            Stars.setSPEED(12);
            Enemy1.setSPEED(12);
        } else {
            timer.setDelay(5);
            Stars.setSPEED(4);
            Enemy1.setSPEED(4);
        }
        for (int p = 0; p < stars.size(); p++) {
            Stars on = stars.get(p);
            if (on.isVisible()) {
                on.update();
            } else {
                stars.remove(p);
            }
        }

        List<Shot> shots = player.getShots();
        for (int i = 0; i < shots.size(); i++) {
            Shot m = shots.get(i);
            if (m.isVisible()) {
                m.update();
                if (player.isTurbo()) {
                    shots.get(i).setSPEED(-1);
                }
                if (!player.isTurbo()) {
                    shots.get(i).setSPEED(7);
                }
            } else {
                shots.remove(i);
            }
        }

        for (int o = 0; o < enemy1.size(); o++) {
            Enemy1 in = enemy1.get(o);
            if (in.isVisible()) {
                in.update();
            } else {
                enemy1.remove(o);
            }
        }
        checkCollisions();
        repaint();
    }

    public void checkCollisions() {
        Rectangle shapeShip = player.getBounds();
        Rectangle shapeEnemy1;
        Rectangle shapeShot;

        for (int i = 0; i < enemy1.size(); i++) {
            Enemy1 tempEnemy1 = enemy1.get(i);
            shapeEnemy1 = tempEnemy1.getBounds();
            if (shapeShip.intersects(shapeEnemy1)) {
                if (player.isTurbo()) {
                    tempEnemy1.setVisible(false);
                } else {
                    player.setVisible(false);
                    tempEnemy1.setVisible(false);
                    inGame = false;
                }
            }
        }

        List<Shot> shots = player.getShots();
        for (int j = 0; j < shots.size(); j++) {
            Shot tempShot = shots.get(j);
            shapeShot = tempShot.getBounds();
            for (int z = 0; z < enemy1.size(); z++) {
                Enemy1 tempEnemy1 = enemy1.get(z);
                shapeEnemy1 = tempEnemy1.getBounds();
                if (shapeShot.intersects(shapeEnemy1)) {
                    tempEnemy1.setVisible(false);
                    tempShot.setVisible(false);
                }
            }
        }

    }

    private class keyboardAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyRelease(e);
        }
    }


}
