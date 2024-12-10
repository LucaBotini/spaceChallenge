//package mygame.Model;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import mygame.Container; // Importar o Container corretamente
//
//public class Lobby extends JPanel implements ActionListener {
//    private Image bottom;
//    private boolean inLobby;
//    private Container parentContainer; // Tipo correto do Container
//
//    public Lobby(Container parentContainer) {
//        this.parentContainer = parentContainer;
//        inLobby = true;
//
//        // Configurações do JPanel
//        setFocusable(true);
//        setDoubleBuffered(true);
//        setLayout(null); // Permitir posicionamento absoluto
//
//        // Carregar a imagem do lobby (se necessário)
//        String path = "src\\res\\lobby.png";
//        ImageIcon reference = new ImageIcon(path);
//        bottom = reference.getImage();
//
//        // Criar o botão
//        JButton jButton = new JButton("START");
//        jButton.setBounds(100, 100, 100, 50); // Posição x, y e dimensões (largura, altura)
//        jButton.addActionListener(this); // Adicionar listener ao botão
//
//        // Adicionar o botão ao JPanel
//        this.add(jButton);
//    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//
//        // Desenhar o fundo
//        if (bottom != null) {
//            g.drawImage(bottom, 0, 0, getWidth(), getHeight(), this);
//        }
//
//        if (inLobby) {
//            Graphics2D graphics = (Graphics2D) g;
//            graphics.setFont(new Font("Arial", Font.BOLD, 20)); // Configura a fonte
//            graphics.setColor(Color.RED); // Configura a cor do texto
//
//            // Exibir texto, se necessário
////            graphics.drawString("lobby: ", 10, 30);
////            graphics.drawString("Objective: 30 kills", 10, 60);
//        }
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        parentContainer.changeToPhase(); // Chama o método changeToPhase no Container
//    }
//
//    public boolean isInLobby() {
//        return inLobby;
//    }
//}
