package biocenosismodel;

public class Herbivorous extends Organism {
    double[] enters;
    double[] outers;
    double[][] weights;

    Herbivorous(){
        enters = new double[12];
        outers = new double[4];
        weights = new double[12][4];
        for(int i = 0; i < enters.length; i++){
            for(int j = 0; j < outers.length; j++)
                weights[i][j] = Math.random();
        }
        // instincts
        weights[11][3] = 1; // eat when plant near
        weights[1][2] = -10; // don't move to predator
    }

    @Override
    public int[][] getView() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void action() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
