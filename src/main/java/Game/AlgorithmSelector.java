package Game;

import javax.swing.*;
import java.awt.*;

public class AlgorithmSelector extends JPanel {

    private String selected = "";

    AlgorithmSelector(){

        Color brown = new Color(0x936639);
        this.setBackground(brown);
        this.setLayout(null);

        JLabel title = new JLabel("Select Algorithm");
        title.setBounds(10,0,125,15);

        ButtonGroup group = new ButtonGroup();

        JRadioButton DFS = new JRadioButton("DFS");
        JRadioButton BFS = new JRadioButton("BFS");
        JRadioButton STAR = new JRadioButton("A*");

        DFS.setBackground(brown);
        BFS.setBackground(brown);
        STAR.setBackground(brown);

        DFS.setFocusPainted(false);
        BFS.setFocusPainted(false);
        STAR.setFocusPainted(false);

        DFS.setBounds(10,25,70,10);
        BFS.setBounds(10,40,70,10);
        STAR.setBounds(10,55,70,10);

        DFS.addActionListener((_) -> selected = "DFS");
        BFS.addActionListener((_) -> selected = "BFS");
        STAR.addActionListener((_) -> selected = "STAR");

        group.add(DFS);
        group.add(BFS);
        group.add(STAR);

        this.add(title);
        this.add(DFS);
        this.add(BFS);
        this.add(STAR);
    }

    public String getSelected() {
        return this.selected;
    }
}
