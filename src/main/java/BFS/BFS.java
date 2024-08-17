package BFS;

import Node.Node;
import static Direction.Direction.*;

import javax.swing.*;
import java.util.ArrayDeque;
import java.util.Queue;


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
                visitPotentialNode(canvas,currentNode,leftNode);

                if(leftNode.isGoal()){
                    break;
                }
            }

            if(currentNode.x() != maze.length - 1){

                Node bottomNode = maze[currentNode.x() + DOWN.move()][currentNode.y()];
                visitPotentialNode(canvas,currentNode,bottomNode);

                if(bottomNode.isGoal()){
                    break;
                }
            }

            if(currentNode.y() != maze.length - 1){

                Node rightNode = maze[currentNode.x()][currentNode.y() + RIGHT.move()];
                visitPotentialNode(canvas,currentNode,rightNode);

                if(rightNode.isGoal()){
                    break;
                }
            }

            if(currentNode.x() != 0){

                Node topNode = maze[currentNode.x() + UP.move()][currentNode.y()];
                visitPotentialNode(canvas,currentNode,topNode);

                if(topNode.isGoal()){
                    break;
                }
            }
        }
    }

    private void visitPotentialNode(JPanel canvas, Node current, Node neighbour) {
        if(neighbour.isNotVisited() && neighbour.isNotObstacle()){

            neighbour.setVisited(true);
            neighbour.setParent(current);
            nodes.offer(neighbour);
            canvas.repaint();
        }
    }
}
