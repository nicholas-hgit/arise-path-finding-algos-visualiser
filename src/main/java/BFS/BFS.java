package BFS;

import Game.GameStage;
import Node.Node;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayDeque;
import java.util.Queue;
import static Direction.Direction.*;
import static java.awt.Color.PINK;

public class BFS {

    private final Queue<Node> nodes;

    public BFS(){
        nodes = new ArrayDeque<>();
    }

    public void start(Node[][] maze, Node start, Node goal, JPanel canvas){

        nodes.offer(start);
        start.setVisited(true);

        while (!nodes.isEmpty() && goal.isNotVisited()){

            Node currentNode = nodes.poll();

            assert currentNode != null;
            if(currentNode.y() != 0){

                Node leftNode = maze[currentNode.x()][currentNode.y() + LEFT.move()];

                if(leftNode.isNotVisited() && leftNode.isNotObstacle()){

                    leftNode.setVisited(true);
                    leftNode.setParent(currentNode);
                    nodes.offer(leftNode);
                    canvas.repaint();
                }
            }

            if(currentNode.x() != maze.length - 1){

                Node bottomNode = maze[currentNode.x() + DOWN.move()][currentNode.y()];

                if(bottomNode.isNotVisited() && bottomNode.isNotObstacle()){

                    bottomNode.setVisited(true);
                    bottomNode.setParent(currentNode);
                    nodes.offer(bottomNode);
                    canvas.repaint();
                }
            }

            if(currentNode.y() != maze.length - 1){

                Node rightNode = maze[currentNode.x()][currentNode.y() + RIGHT.move()];

                if(rightNode.isNotVisited() && rightNode.isNotObstacle()){

                    rightNode.setVisited(true);
                    rightNode.setParent(currentNode);
                    nodes.offer(rightNode);
                    canvas.repaint();
                }
            }

            if(currentNode.x() != 0){

                Node topNode = maze[currentNode.x() + UP.move()][currentNode.y()];

                if (topNode.isNotVisited() && topNode.isNotObstacle()){

                    topNode.setVisited(true);
                    topNode.setParent(currentNode);
                    nodes.offer(topNode);
                    canvas.repaint();
                }
            }
        }
    }
}
