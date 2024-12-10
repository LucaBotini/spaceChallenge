package mygame;

import java.awt.Component;
import javax.swing.*;

import mygame.Model.Phase;

public class Container extends JFrame {
    public Container() {
        this.add(new Phase());
        this.setTitle("Space Challenge 🚀");
        ImageIcon icone = new ImageIcon("src/res/space.png");
        this.setIconImage(icone.getImage());
        this.setSize(1024, 728);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo((Component)null);
        this.setResizable(false);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Container();
    }
}
