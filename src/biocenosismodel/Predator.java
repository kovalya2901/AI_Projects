package biocenosismodel;

public class Predator extends Organism {
    double[] enters;
    double[] outers;
    double[][] weights;

    Predator(){
        enters = new double[12];
        outers = new double[4];
        weights = new double[12][4];
        for(int i = 0; i < enters.length; i++){
            for(int j = 0; j < outers.length; j++)
                weights[i][j] = Math.random();
        }
        // instincts
        weights[9][3] = 1; // eat when herbivorous near
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
