package Node;

import java.util.Objects;

public class Node {

    private final int x;
    private final int y;
    private boolean isObstacle;
    private boolean isVisited;
    private final boolean isStart;
    private final boolean isGoal;

    private Node parent;

    //A* variables
    private int g;
    private int h;


    public Node(int x,int y, boolean isStart, boolean isGoal){
        this.x = x;
        this.y = y;
        this.isStart = isStart;
        this.isGoal = isGoal;
        this.isObstacle = false;
        this.isVisited = false;
    }

    public int x(){
        return this.x;
    }

    public int y(){
        return this.y;
    }

    public boolean isStart(){
        return this.isStart;
    }

    public boolean isGoal(){
        return this.isGoal;
    }

    public boolean isNotVisited() {
        return !this.isVisited;
    }

    public void setVisited(boolean isVisited){
        this.isVisited = isVisited;
    }

    public void setObstacle(boolean isObstacle){
        this.isObstacle = isObstacle;
    }

    public boolean isNotObstacle(){
        return !this.isObstacle;
    }

    public void setParent(Node parent){
        this.parent = parent;
    }

    public Node parent(){
        return this.parent;
    }

    public void setG(int g){
        this.g = g;
    }

    public int g(){
        return this.g;
    }

    public void setH(int h){
        this.h = h;
    }

    public int h(){
        return this.h;
    }

    public int f(){
        return this.h + this.g;
    }

    @Override
    public String toString() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                ", isStart=" + isStart +
                ", isGoal=" + isGoal +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node node)) return false;
        return x == node.x && y == node.y;
    }


    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
