import data.BankMarketing;
import serial.KnnSerialClassifier;

import java.util.List;

public class KnnClassificationSerial {
    private final List<BankMarketing> test;
    private final List<BankMarketing> train;
    private int k;

    public KnnClassificationSerial(int k, List<BankMarketing> train, List<BankMarketing> test) {
        this.k = k;
        this.test=test;
        this.train=train;
    }

    public void run(){
        KnnSerialClassifier classifier = new KnnSerialClassifier(train, k);
        for (BankMarketing example : test) {
            classifier.classify(example);
        }
    }
}
