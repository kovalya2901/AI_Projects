package biocenosismodel;

public abstract class Organism {
    static final int NORTH = 0;
    static final int SOUTH = 1;
    static final int WEST = 2;
    static final int EAST = 3;

    double metabolism;

    int x;
    int y;

    int direction;

    double energy;

    public abstract int[][] getView();

    public abstract void action();

    public void setMetabolism(double metabolism){
        this.metabolism = metabolism;
    }

    public void setCoords(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setDirection(int direction){
        this.direction = direction;
    }
}
