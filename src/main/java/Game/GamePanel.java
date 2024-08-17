package Game;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel{

    public GamePanel(){

        Color brown = new Color(0x936639);
        this.setLayout(null);
        this.setBackground(brown);

        GridSelector gridSelector = new GridSelector();
        AlgorithmSelector algorithmSelector = new AlgorithmSelector();

        JLabel startAndGoalInstruction = new JLabel("Enter Start and Goal");
        JLabel xCoordinate = new JLabel("x");
        JLabel yCoordinate = new JLabel("y");

        JTextField startX = new JTextField();
        JTextField startY = new JTextField();
        JTextField goalX = new JTextField();
        JTextField goalY = new JTextField();

        gridSelector.setBounds(10,10,120,70);
        algorithmSelector.setBounds(140,10,125,70);
        startAndGoalInstruction.setBounds(290,11,150,10);

        xCoordinate.setBounds(290,30,25,15);
        yCoordinate.setBounds(330,30,25,15);

        startX.setBounds(290,50,25,15);
        startY.setBounds(330,50,25,15);
        goalX.setBounds(290,70,25,15);
        goalY.setBounds(330,70,25,15);

        JButton generateGrid = new JButton("Generate grid");
        generateGrid.setBounds(10,500,150,40);
        generateGrid.setFocusPainted(false);

        JButton findPath = new JButton("Start algorithm");
        findPath.setBounds(165,500,150,40);
        findPath.setFocusPainted(false);

        JButton quit = new JButton("Close window");
        quit.setBounds(320,500,150,40);
        quit.setFocusPainted(false);

        generateGrid.addActionListener((_) -> onGenerateGrid(gridSelector,startX,startY,goalX,goalY));

        quit.addActionListener((_) -> SwingUtilities.getWindowAncestor(this).dispose());
        findPath.addActionListener((_) -> {
            Component component = this.getComponent(11);
            if(component instanceof GameStage g){
                g.findPath(algorithmSelector.getSelected());
            }
        });


        this.add(gridSelector);
        this.add(algorithmSelector);
        this.add(startAndGoalInstruction);
        this.add(xCoordinate);
        this.add(yCoordinate);
        this.add(startX);
        this.add(startY);
        this.add(goalX);
        this.add(goalY);
        this.add(generateGrid);
        this.add(findPath);
        this.add(quit);

    }

    private GameStage generateGrid(String size, Point start, Point goal) {
        GameStage gameStage = new GameStage(size, start, goal);
        gameStage.setBounds(40, 90, 400, 400);

        return  gameStage;
    }

    private void onGenerateGrid(GridSelector selector, JTextField sX, JTextField sY, JTextField gX, JTextField gY){

        if(this.getComponentCount() > 11){
            this.remove(11);
        }

        Point start;
        Point goal;

        try{
            start = new Point(Integer.parseInt(sX.getText()), Integer.parseInt(sY.getText()));
            goal = new Point(Integer.parseInt(gX.getText()), Integer.parseInt(gY.getText()));
        }catch (NumberFormatException _){
            return;
        }

        String gridSize = selector.getSelected();

        if(gridSize.isEmpty() || start.equals(goal)){
            return;
        }

        if("small".equals(gridSize) && (start.x + start.y >= 16 || goal.x + goal.y >= 16)){
            return;
        }

        if("medium".equals(gridSize) && (start.x + start.y >= 32 || goal.x + goal.y >= 32)){
            return;
        }

        if("large".equals(gridSize) && (start.x + start.y >= 64 || goal.x + goal.y >= 64)){
            return;
        }

        GameStage gameStage = generateGrid(gridSize, start, goal);
        this.add(gameStage);
        this.validate();
        gameStage.repaint();
    }
}
