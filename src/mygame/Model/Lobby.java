package mygame.Model;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import mygame.Container; // Importar o Container corretamente

public class Lobby extends JPanel implements ActionListener {
    private Image bottom;
    private boolean inLobby;
    private Container parentContainer; // Tipo correto do Container
    private Font pixelFont = null;
    private Clip music;

    public Lobby(Container parentContainer) {
        this.parentContainer = parentContainer;
        inLobby = true;
        playSoundtrack("src\\res\\80s-Action-Heroes.wav");
        // Configurações do JPanel
        setFocusable(true);
        setDoubleBuffered(true);
        setLayout(null); // Permitir posicionamento absoluto

        // Carregar a imagem do lobby (se necessário)
        String path = "src\\res\\lobby.png";
        ImageIcon reference = new ImageIcon(path);
        bottom = reference.getImage();


        try {
            pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/res/PressStart2P-Regular.ttf")).deriveFont(18f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        // Criar o botão
        JButton jButton = new JButton("START");
        jButton.setBounds(447, 450, 130, 50); // Posição e tamanho
        if (pixelFont != null) {
            jButton.setFont(pixelFont);
        }
        jButton.setBackground(new Color(250, 0, 0)); // Cor de fundo
        jButton.setForeground(Color.WHITE); // Cor do texto
        jButton.setFocusPainted(false); // Remove a borda de foco
        jButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // Borda personalizada

        jButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton.setBackground(new Color(102, 105, 108)); // Cor ao passar o mouse
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton.setBackground(new Color(250, 0, 0)); // Cor padrão
            }
        });
        // Adicionar o botão ao JPanel e dar interatividade.
        jButton.addActionListener(this);
        this.add(jButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Desenhar o fundo
        if (bottom != null) {
            g.drawImage(bottom, 0, 0, getWidth(), getHeight(), this);
        }

        if (inLobby) {
            Graphics2D graphics = (Graphics2D) g;

            graphics.setFont(pixelFont); // Configura a fonte
            graphics.setColor(Color.WHITE); // Cofigura a cor do texto
            graphics.drawString("UPDATES 12/12/24 - v2.0.1: ", 280, 540);
            graphics.drawString("- New lobby", 200, 575);
            graphics.drawString("- Objective: 30 kills", 200, 600);
            graphics.drawString("- 500ms timer for next shot", 200, 625);
            graphics.drawString("- New victory and defeat screens", 200, 650);
            graphics.drawString("- Turbo only one use.", 200, 675);
        }
    }

    public void playSoundtrack(String soundFilePath) {
        try {
            if (music != null) {
                if (music.isRunning()) {
                    music.stop();
                }
                music.close();
            }

            // Carrega o arquivo de áudio
            File soundFile = new File(soundFilePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            // Obtém o formato e a linha de áudio
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            music = (Clip) AudioSystem.getLine(info);
            // Abre o áudio e toca
            music.open(audioStream);
            music.loop(Clip.LOOP_CONTINUOUSLY);
            music.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public void stopSoundTrack() {
        if (music != null && music.isRunning()) {
            music.stop();
            music.close();
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        inLobby = false;
        stopSoundTrack();
        parentContainer.changeToPhase();
    }

    public boolean isInLobby() {
        return inLobby;
    }
}
