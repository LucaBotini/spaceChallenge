package mygame;

import java.awt.Component;
import javax.swing.*;

//import mygame.Model.Lobby;
import mygame.Model.Phase;

public class Container extends JFrame {
    public Container() {
//        this.add(new Lobby(this));
        this.add(new Phase());
        this.setTitle("Space Challenge 🚀");
        ImageIcon icone = new ImageIcon("src/res/space.png");
        this.setIconImage(icone.getImage());
        this.setSize(1024, 728);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo((Component)null);
        this.setResizable(false);
        this.setVisible(true);
    }

//    public void changeToPhase() {
//        this.getContentPane().removeAll(); // Remove o painel atual
//        this.add(new Phase()); // Adiciona o novo painel
//        this.revalidate(); // Atualiza o layout
//        this.repaint(); // Re-renderiza a janela
//    }

    public static void main(String[] args) {
        new Container();
    }
}
