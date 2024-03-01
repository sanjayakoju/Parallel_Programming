import util.MatrixUtil;

public class Matrix {
    private double [][] A;
    private double [][] B;
    private int size=0;

    boolean done=true;

    public Matrix() {
    }

    public Matrix(int size) {
        this.size=size;
        A = MatrixUtil.generate(size);
        B = new double[size][size];
        MatrixUtil.copy(A,B);
    }

    public double[][] getA() {
        return A;
    }

    public double[][] getB() {
        return B;
    }

    public int getSize() {
        return size;
    }

    public boolean isDone() {
        return done;
    }

    public synchronized void setDone(boolean done) {
        this.done = done;
    }

    public synchronized void and(boolean done){
        this.done = this.done&done;
    }

    public void setAtA(int i,int j,double value){
        A[i][j]=value;
    }
    public void setAtB(int i,int j,double value){
        B[i][j]=value;
    }
    public double getAtA(int i,int j){
        return A[i][j];
    }
    public double getAtB(int i,int j){
        return B[i][j];
    }
    public void print() {
        System.out.println("=============================================================");
        for(int i =0; i<size;i++){
            for(int j=0;j<size;j++){
                System.out.printf("%5s ",A[i][j]);
            }
            System.out.println();
        }
        System.out.println("=============================================================");
    }


    protected Matrix cloneMatrix() {
        if(size==0) return null;
        Matrix matrix = new Matrix();
        matrix.size = size;
        matrix.A = new double[size][size];
        matrix.B = new double[size][size];
        MatrixUtil.copy(A,matrix.A);
        MatrixUtil.copy(B,matrix.B);
        return matrix;
    }
}
