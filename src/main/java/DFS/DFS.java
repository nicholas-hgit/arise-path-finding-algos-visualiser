package DFS;

import java.util.Stack;
import Node.Node;
import javax.swing.*;

public class DFS {

    private final Stack<Node> nodes;
    private final int[] moves = {-1,0,0,1,1,0,0,-1};

    public DFS(){
        nodes = new Stack<>();
    }

    private boolean isValidCoordinates(int x, int y, int gridSize){
        return (x >= 0 && x <= gridSize - 1) && (y >= 0 && y <= gridSize - 1);

    }

    public void start(Node[][] maze, Node start, Node goal, JPanel canvas){

        nodes.push(start);
        start.setVisited(true);

        while (!nodes.isEmpty() && goal.isNotVisited()){

            Node current = nodes.peek();
            boolean updatedNodes = false;

            for(int index = 0; index < 7; index += 2){

                int neighbourX = current.x() + moves[index + 1];
                int neighbourY = current.y() + moves[index];

                if(isValidCoordinates(neighbourX,neighbourY,maze.length)){

                    Node neighbour = maze[neighbourX][neighbourY];
                    if(neighbour.isNotVisited() && neighbour.isNotObstacle()){

                        neighbour.setVisited(true);
                        neighbour.setParent(current);
                        nodes.push(neighbour);
                        updatedNodes = true;
                        canvas.repaint();
                        break;
                    }

                }
            }

            if(!updatedNodes){
                nodes.pop();
            }

        }

    }
}
