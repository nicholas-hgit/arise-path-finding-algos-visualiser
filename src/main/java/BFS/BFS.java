package BFS;

import Node.Node;

import javax.swing.*;
import java.util.ArrayDeque;
import java.util.Queue;


public class BFS {

    private final Queue<Node> nodes;
    private final int[] moves = {-1,0,0,1,1,0,0,-1};

    public BFS(){
        nodes = new ArrayDeque<>();
    }


    private boolean isValidCoordinates(int x, int y, int gridSize){
        return (x >= 0 && x <= gridSize - 1) && (y >= 0 && y <= gridSize - 1);

    }

    private void visitPotentialNode(JPanel canvas, Node current, Node neighbour) {
        if(neighbour.isNotVisited() && neighbour.isNotObstacle()){

            neighbour.setVisited(true);
            neighbour.setParent(current);
            nodes.offer(neighbour);
            canvas.repaint();
        }
    }

    public void start(Node[][] maze, Node start, Node goal, JPanel canvas){

        nodes.offer(start);
        start.setVisited(true);

        while (!nodes.isEmpty() && goal.isNotVisited()){

            Node current = nodes.poll();

            assert current != null;
            if(current.isGoal()){
                break;
            }

            for(int index = 0; index < 7; index += 2){

                int neighbourX = current.x() + moves[index + 1];
                int neighbourY = current.y() + moves[index];

                if(isValidCoordinates(neighbourX,neighbourY,maze.length)){

                    Node neighbour = maze[neighbourX][neighbourY];
                    visitPotentialNode(canvas,current,neighbour);
                }
            }

        }
    }
}
