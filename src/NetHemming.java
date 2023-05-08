import java.util.Arrays;

public class NetHemming {
    private int[] states;
    private int[][] weights;

    public NetHemming(int size) {
        states = new int[size];
        weights = new int[size][size];
    }

    public void train(int[] pattern) {
        for (int i = 0; i < pattern.length; i++) {
            for (int j = 0; j < pattern.length; j++) {
                weights[i][j] += pattern[i] * pattern[j];
            }
        }
    }

    public int[] recognize(int[] pattern) {
        int[] output = new int[states.length];
        for (int i = 0; i < states.length; i++) {
            int activation = 0;
            for (int j = 0; j < states.length; j++) {
                activation += weights[i][j] * pattern[j];
            }
            output[i] = (activation >= 0) ? 1 : -1;
        }
        return output;
    }

    public static void main(String[] args) {
        NetHemming network = new NetHemming(4);
        int[] pattern1 = {1, -1, 1, -1};
        int[] pattern2 = {-1, 1, -1, 1};
        network.train(pattern1);
        network.train(pattern2);
        int[] testPattern = {1, -1, -1, 1};
        int[] result = network.recognize(testPattern);
        System.out.println(Arrays.toString(result));
    }
}
