package DFS;

import java.util.Stack;

import static Direction.Direction.*;
import Node.Node;

import javax.swing.*;

public class DFS {

    private final Stack<Node> nodes;

    public DFS(){
        nodes = new Stack<>();
    }

    public void start(Node[][] maze, Node start, Node goal, JPanel canvas){

        nodes.push(start);
        start.setVisited(true);

        while (!nodes.isEmpty() && goal.isNotVisited()){

            Node currentNode = nodes.peek();

            if(currentNode.y() != 0){

                Node leftNode = maze[currentNode.x()][currentNode.y() + LEFT.move()];

                if(leftNode.isNotVisited() && leftNode.isNotObstacle()){

                    leftNode.setVisited(true);
                    leftNode.setParent(currentNode);
                    nodes.push(leftNode);
                    canvas.repaint();
                    continue;
                }
            }

            if(currentNode.x() != maze.length - 1){

                Node bottomNode = maze[currentNode.x() + DOWN.move()][currentNode.y()];

                if(bottomNode.isNotVisited() && bottomNode.isNotObstacle()){

                    bottomNode.setVisited(true);
                    bottomNode.setParent(currentNode);
                    nodes.push(bottomNode);
                    canvas.repaint();
                    continue;
                }
            }

            if(currentNode.y() != maze.length - 1){

                Node rightNode = maze[currentNode.x()][currentNode.y() + RIGHT.move()];

                if(rightNode.isNotVisited() && rightNode.isNotObstacle()){

                    rightNode.setVisited(true);
                    rightNode.setParent(currentNode);
                    nodes.push(rightNode);
                    canvas.repaint();
                    continue;
                }
            }

            if(currentNode.x() != 0){

                Node topNode = maze[currentNode.x() + UP.move()][currentNode.y()];

                if(topNode.isNotVisited() && topNode.isNotObstacle()){

                    topNode.setVisited(true);
                    topNode.setParent(currentNode);
                    nodes.push(topNode);
                    canvas.repaint();
                    continue;
                }
            }

            nodes.pop();


        }

    }
}
