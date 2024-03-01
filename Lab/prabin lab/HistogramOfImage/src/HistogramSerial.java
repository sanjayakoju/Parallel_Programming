import java.util.Arrays;

public class HistogramSerial {
    int image [][];
    int hist[];
    int max;
    public HistogramSerial(int size,int maxIntensity) {
        this.max=maxIntensity;
        this.hist = new int[maxIntensity];
        this.image = ImageDataLoader.getImageData(size,max);
    }
    public void run(){
        long startTime = System.currentTimeMillis();
        int[] hist = createHistArray();
        long serialTime = System.currentTimeMillis()-startTime;
        System.out.println("Max 10 Serial Time: "+serialTime);
        System.out.println(Arrays.toString(hist));
    }
    private int[] createHistArray(){
        for(int i=0;i<image.length;i++){
            for(int j=0;j<image.length;j++){
                int intensity = image[i][j]%max;
                hist[intensity]++;
            }
        }
        return hist;
    }
}
