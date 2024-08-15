package Game;

import javax.swing.*;
import java.awt.*;

public class GridSelector extends JPanel {

    private String selected = "";

    GridSelector(){

        Color brown = new Color(0x936639);
        this.setBackground(brown);
        this.setLayout(null);

        JLabel title = new JLabel("Select grid size");
        title.setBounds(10,0,120,15);

        ButtonGroup group = new ButtonGroup();

        JRadioButton smallGrid = new JRadioButton("8x8");
        JRadioButton mediumGrid = new JRadioButton("16x16");
        JRadioButton largeGrid = new JRadioButton("32x32");

        smallGrid.setBackground(brown);
        mediumGrid.setBackground(brown);
        largeGrid.setBackground(brown);

        smallGrid.setFocusPainted(false);
        mediumGrid.setFocusPainted(false);
        largeGrid.setFocusPainted(false);

        smallGrid.setBounds(10,25,50,10);
        mediumGrid.setBounds(10,40,70,10);
        largeGrid.setBounds(10,55,70,10);

        smallGrid.addActionListener((_) -> selected = "small");
        mediumGrid.addActionListener((_) -> selected = "medium");
        largeGrid.addActionListener((_) -> selected = "large");

        group.add(smallGrid);
        group.add(mediumGrid);
        group.add(largeGrid);

        this.add(title);
        this.add(smallGrid);
        this.add(mediumGrid);
        this.add(largeGrid);
    }

    public String getSelected(){
        return this.selected;
    }
}
