package util;

import java.util.Arrays;
import java.util.Random;

public class GenerateArray {

    public static int[] generate(int size){
        int[] arr = new int[size];
        for(int i=0;i<size;i++){
            arr[i]=new Random().nextInt(999999999);
        }
        return arr;
    }
}
