import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class NetHopfield {
    File file1 = new File("C:\\Users\\User\\Desktop\\tests\\A_orig.png");
    BufferedImage imageA = ImageIO.read(file1);
    public int[][] example;
    public int[][] weights;
    public NetHopfield() throws IOException {
        example = new int[1][imageA.getHeight() * imageA.getWidth()];
        weights = new int[example.length][example.length];
        getImage();
        study();
    }
    public void getImage(){
        //Color you are searching for
        Color color = Color.BLACK; //Green in this example
        for (int x = 0;x < imageA.getHeight(); x++){
            for (int y = 0; y < imageA.getWidth(); y++){
                if(imageA.getRGB(x, y) == color.getRGB()){
                    example[0][imageA.getWidth()*y+x] = 1;
                }
                else {
                    example[0][imageA.getWidth()*y+x] = -1;
                }
            }
        }
    }
    public int[][] transposeMatrix(int [][] m){
        int[][] temp = new int[m[0].length][m.length];
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[0].length; j++)
                temp[j][i] = m[i][j];
        return temp;
    }
    public static int[][] multiplicar(int[][] A, int[][] B) {
        int aRows = A.length;
        int aColumns = A[0].length;
        int bRows = B.length;
        int bColumns = B[0].length;
        if (aColumns != bRows) {
            throw new IllegalArgumentException("A:Rows: " + aColumns + " did not match B:Columns " + bRows + ".");
        }
        int[][] C = new int[aRows][bColumns];
        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bColumns; j++) {
                C[i][j] = 0;
            }
        }
        for (int i = 0; i < aRows; i++) { // aRow
            for (int j = 0; j < bColumns; j++) { // bColumn
                for (int k = 0; k < aColumns; k++) { // aColumn
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }
    public void study(){
        weights = multiplicar(transposeMatrix(example),example);
        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < weights[0].length; j++) {
                weights[i][j] = i == j ? 0 : weights[i][j];
            }
        }
    }
    public void remember(String path) throws IOException {

        File testFile = new File(path);
        BufferedImage image = ImageIO.read(testFile);
        getImage();
        int[][] answer = new int[1][image.getHeight() * image.getWidth()];
        for (int i = 0; i < weights.length; i++) {
            int s = 0;
            for (int j = 0; j < example[0].length; j++) {
                s += example[0][j] * weights[j][i];
            }
            answer[0][i] = s > 0 ? 1 : 0;
        }

        for (int j = 0; j < answer[0].length; j++) {
            if(j != 0 && j % 30 == 0){
                System.out.println(answer[0][j]);
            }
            else {
                System.out.print(answer[0][j]);
            }
        }
    }

}
