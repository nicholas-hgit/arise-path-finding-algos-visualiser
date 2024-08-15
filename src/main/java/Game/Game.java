package Game;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {

    private Game(){
        this.setTitle("PATH FINDER");
        this.setSize(new Dimension(485,600));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.add(new GamePanel());
        this.setVisible(true);
    }

    public static void start(){
        new Game();
    }
}
