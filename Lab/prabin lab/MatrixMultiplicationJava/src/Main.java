public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("\t\t\t\t\t\t\tFor matrix size:"+1000);
        SerialMultiplication.main();
        ThreadPerElement.main();
        ThreadPerRow.main();
        ThreadPerCore.main();
    }
}
