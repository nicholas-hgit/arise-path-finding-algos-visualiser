package STAR;

import Node.Node;

import javax.swing.*;
import java.util.PriorityQueue;
import java.util.Queue;
import static java.lang.Math.*;


public class STAR {

    private final Queue<Node> nodes;
    private final int[] moves = {-1,0,0,1,1,0,0,-1};

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
        return (int)sqrt(pow((goal.x() - node.x()),2) + pow((goal.y() - node.y()),2));
    }

    private boolean isValidCoordinates(int x, int y, int gridSize){
        return (x >= 0 && x <= gridSize - 1) && (y >= 0 && y <= gridSize - 1);

    }

    private void visitPotentialNode(Node goal, Node current, Node neighbour) {
        if(neighbour.isNotVisited() && neighbour.isNotObstacle()){

            int g = current.g() + 1;
            int h = this.calculateH(neighbour,goal);
            int f = g + h;

            boolean isNeighbourInNodes = nodes.contains(neighbour);
            boolean requiresUpdate = false;

            if(isNeighbourInNodes && neighbour.f() > f){
                nodes.remove(neighbour);
                requiresUpdate = true;
            }

            if(!isNeighbourInNodes || requiresUpdate){

                neighbour.setG(g);
                neighbour.setH(h);
                neighbour.setParent(current);
                nodes.offer(neighbour);

            }
        }
    }

    public void start(Node[][] maze, Node start, Node goal, JPanel canvas){

        start.setVisited(true);
        start.setG(0);

        nodes.offer(start);

        while (!nodes.isEmpty()){

            Node current = nodes.poll();
            assert current != null;

            current.setVisited(true);
            canvas.repaint();

            if(current.isGoal()){
                break;
            }

            for(int index = 0; index < 7; index += 2){

                int neighbourX = current.x() + moves[index + 1];
                int neighbourY = current.y() + moves[index];

                if(isValidCoordinates(neighbourX,neighbourY,maze.length)){

                    Node neighbour = maze[neighbourX][neighbourY];
                    visitPotentialNode(goal,current,neighbour);
                }
            }

        }
    }
}
