package serial;

import util.Distance;
import util.EuclideanDistanceCalculator;
import util.Sample;

import java.util.*;


public class KnnSerialClassifier {

    private final List<? extends Sample> dataSet;
    private int k;

    public KnnSerialClassifier(List<? extends Sample> dataSet, int k) {
        this.dataSet=dataSet;
        this.k=k;
    }

    public String classify (Sample example) {

        Distance[] distances=new Distance[dataSet.size()];

        int index=0;

        for (Sample localExample : dataSet) {
            Distance distance = new Distance();
            distance.setIndex(index);
            distance.setDistance(EuclideanDistanceCalculator.calculate(localExample, example));
            distances[index] = distance;
            index++;
        }

        Arrays.sort(distances);

        Map<String, Integer> results = new HashMap<>();
        for (int i = 0; i < k; i++) {
            Sample localExample = dataSet.get(distances[i].getIndex());
            String tag = localExample.getTag();
            results.merge(tag, 1, (a, b) -> a+b);
        }



        return Collections.max(results.entrySet(),
                Map.Entry.comparingByValue()).getKey();
    }
}
