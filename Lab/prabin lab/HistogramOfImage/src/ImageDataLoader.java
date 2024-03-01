public class ImageDataLoader {

    public static int[][] getImageData(int n,int max){
        int[][] image = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                image[i][j] = (i+j)%max;
        }
        return image;
    }
}
