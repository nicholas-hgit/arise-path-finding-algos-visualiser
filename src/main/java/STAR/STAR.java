package STAR;

import Node.Node;

import javax.swing.*;
import java.util.PriorityQueue;
import java.util.Queue;

import static Direction.Direction.*;

public class STAR {

    private final Queue<Node> nodes;

    public STAR(){
        this.nodes = new PriorityQueue<>((Node n1, Node n2) -> {

            if(n1.f() == n2.f()){
                if(n1.h() == n2.h()){
                    return Integer.compare(n1.g(), n2.g());
                }
                return Integer.compare(n1.h(), n2.h());
            }
            return Integer.compare(n1.f(), n2.f());
        });
    }


    private int calculateH(Node node, Node goal){
        return (int)Math.sqrt(Math.abs(goal.x() - node.x()) + Math.abs(goal.y() - node.y()));
    }

    public void start(Node[][] maze, Node start, Node goal, JPanel canvas){

        start.setVisited(true);
        start.setG(0);

        nodes.offer(start);

        while (!nodes.isEmpty() && goal.isNotVisited()){

            Node current = nodes.poll();

            assert current != null;
            if(current.y() != 0){

                Node leftNode = maze[current.x()][current.y() + LEFT.move()];
                visitPotentialNode(goal, canvas, current, leftNode);

                if(leftNode.isGoal()){
                    break;
                }
            }

            if(current.x() != maze.length - 1){

                Node bottomNode = maze[current.x() + DOWN.move()][current.y()];
                visitPotentialNode(goal, canvas, current, bottomNode);

                if(bottomNode.isGoal()){
                    break;
                }

            }

            if(current.y() != maze.length - 1){

                Node rightNode = maze[current.x()][current.y() + RIGHT.move()];
                visitPotentialNode(goal, canvas, current, rightNode);

                if(rightNode.isGoal()){
                    break;
                }
            }

            if(current.x() != 0){

                Node topNode = maze[current.x() + UP.move()][current.y()];
                visitPotentialNode(goal, canvas, current, topNode);

                if(topNode.isGoal()){
                    break;
                }
            }
        }
    }

    private void visitPotentialNode(Node goal, JPanel canvas, Node current, Node neighbour) {
        if(neighbour.isNotVisited() && neighbour.isNotObstacle()){

            neighbour.setG(current.g() + 1);
            neighbour.setH(this.calculateH(neighbour,goal));
            neighbour.setVisited(true);
            neighbour.setParent(current);
            nodes.offer(neighbour);
            canvas.repaint();
        }
    }
}
