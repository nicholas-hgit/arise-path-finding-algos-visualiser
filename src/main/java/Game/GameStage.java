package Game;

import BFS.BFS;
import DFS.DFS;
import Node.Node;
import STAR.STAR;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static java.awt.Color.*;

public class GameStage extends JPanel {

    private final int cellSize;
    private final Node[][] grid;
    private Node start;
    private Node goal;

    private final DFS dfs;
    private final BFS bfs;
    private final STAR star;

    GameStage(String gridSize, Point start, Point goal){
        this.setBackground(new Color(0x936639));

        this.dfs = new DFS();
        this.bfs = new BFS();
        this.star = new STAR();

        this.grid = generateGrid(gridSize, start, goal);
        this.cellSize = 400 / grid.length;

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int ri = e.getY() / cellSize;
                int ci = e.getX() / cellSize;

                grid[ri][ci].setObstacle(true);
                repaint();

            }
        });

    }

    public Node[][] generateGrid(String gridSize,Point start,Point goal){

        int size = switch (gridSize){
            case "medium" -> 16;
            case "large" -> 32;
            default -> 8;
        };

        Node[][] grid = new Node[size][size];
        for(int ri = 0; ri < size; ++ri){
            for(int ci = 0; ci < size; ++ci){

                boolean isStart = ri == start.x && ci == start.y;
                boolean isGoal = ri == goal.x && ci == goal.y;

                Node node = new Node(ri,ci,isStart,isGoal);

                if(isStart){
                    this.start = node;
                } else if (isGoal) {
                    this.goal = node;

                }

                grid[ri][ci] = node;
            }
        }

        return grid;
    }

    public void findPath(String algorithm){
        switch (algorithm){
            case "BFS" -> bfs.start(grid, start, goal,this);
            case "STAR" -> star.start(grid, start, goal, this);
            default -> dfs.start(grid, start, goal,this);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int size = grid.length;
        for(int ri = 0; ri < size; ++ri){
            for(int ci = 0; ci < size; ++ci){

                Node node = grid[ri][ci];

                Color color = node.isNotObstacle() ? GREEN : GRAY;
                g.setColor(color);

                if(node.isStart()){
                    g.setColor(RED);
                } else if (node.isGoal()) {
                    g.setColor(BLUE);
                } else if (!node.isNotVisited()) {
                    g.setColor(YELLOW);
                }


                g.fillRect(ci * cellSize, ri * cellSize, cellSize, cellSize);
                g.setColor(WHITE);
                g.drawRect(ci * cellSize, ri * cellSize, cellSize, cellSize);
            }

        }

        paintPath(g);

    }



    private void paintPath(Graphics g){

        Node currentNode = goal.parent();
        g.setColor(WHITE);

        while (currentNode != null && currentNode != start){

            g.fillRect(currentNode.y() * cellSize, currentNode.x() * cellSize, cellSize, cellSize);
            currentNode = currentNode.parent();
        }


    }
}
