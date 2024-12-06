package mygame;

import java.awt.Component;
import javax.swing.JFrame;
import mygame.Model.Phase;

public class Container extends JFrame {
    public Container() {
        this.add(new Phase());
        this.setTitle("My Game");
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
