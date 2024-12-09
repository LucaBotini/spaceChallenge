package mygame.Model;

import javax.sound.sampled.AudioFormat;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Player implements ActionListener {
    private Phase phase;
    private int x, y;
    private int dx, dy;
    private Image image, imageTurbo;
    private int height, width;
    private List<Shot> shots;
    private boolean isVisible, isTurbo;
    private Timer timerP;
    private Clip audioClip;

    public Player(Phase phase) {

        this.phase = phase;
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

    public void playSound(String soundFilePath) {
        try {
            // Carrega o arquivo de áudio
            File soundFile = new File(soundFilePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);

            // Obtém o formato e a linha de áudio
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            audioClip = (Clip) AudioSystem.getLine(info);

            // Abre o áudio e toca
            audioClip.open(audioStream);
            audioClip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public void stopSound() {
        if (audioClip != null) {
            System.out.println("Is running: " + audioClip.isRunning());
            if (audioClip.isRunning()) {
                audioClip.stop();
                System.out.println("Audio stopped.");
            }
            audioClip.close();
            System.out.println("Audio resources released.");
            audioClip = null;
        } else {
            System.out.println("No audio to stop.");
        }
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

        ImageIcon reference2 = new ImageIcon("src/res/activedTurbo.png");
        imageTurbo = reference2.getImage();

        height = imageTurbo.getHeight(null);
        width = imageTurbo.getWidth(null);
    }

    public void keyPressed(KeyEvent key) {

        int code = key.getKeyCode();

        switch (code) {
            case KeyEvent.VK_ENTER:
                if (!phase.isInGame()) {
                    phase.restartGame();
                }
                break;
            case KeyEvent.VK_SPACE:
                if (!isTurbo) {
                    simpleShot();
                    playSound("src/res/lasergun.wav");
                }
                break;
            case KeyEvent.VK_SHIFT:
                if (!phase.isActivedTurbo()) {
                    turbo();
                }
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
