public class SimMetallRecovery {
    double[][] dec;

    public SimMetallRecovery(){
        dec = new double[2][2];
        for (int i = 0; i < dec[0].length; i++) {
            dec[0][i] = Math.random();
        }
    }

    public double mark(double[] dec){
        double res = 1 / (1 + Math.pow(dec[0],2) + Math.pow(dec[1],2));
        return res;
    }

    public void anneling(){
        double t = 100000;
        System.out.println(dec[0][0] + " " + dec[0][1] + " " + mark(dec[0]));
        do {
            for (int i = 0; i < dec[1].length; i++){
                dec[1][i] = dec[0][i] + Math.random() * 0.01 - 0.005;
            }
            double energy1 = mark(dec[0]);
            double energy2 = mark(dec[1]);

            if(energy2 < energy1){
                dec[0] = java.util.Arrays.copyOf(dec[1],dec[1].length);
            }else{
                double p = (Math.exp((-1 * (energy2 - energy1)) / t));
                if(Math.random() < p){
                    dec[0] = java.util.Arrays.copyOf(dec[1],dec[1].length);
                }
            }

            System.out.println(dec[0][0] + " " + dec[0][1] + " " + mark(dec[0]));
            t *= 0.99;
        } while (t > 0.00001);
    }
}
