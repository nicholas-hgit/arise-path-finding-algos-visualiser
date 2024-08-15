package Direction;

public enum Direction {

    UP(-1),
    LEFT(-1),
    DOWN(1),
    RIGHT(1);

    private final int move;

    Direction(int move){
        this.move = move;
    }

    public int move(){
        return this.move;
    }

}
