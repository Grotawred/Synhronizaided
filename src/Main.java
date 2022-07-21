class Array5{
    synchronized public int[] Get(String threadName) {
        System.out.println("Begin => " + threadName);

        int[] AI = new int[5];
        for ( int i = 0; i < AI.length; i++ )
            AI[i] = i + 1;

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.print(threadName + " = [ ");
        for ( int i = 0; i < AI.length; i++ )
            System.out.print(AI[i] + " ");
        System.out.println(" ]");

        System.out.println("End => " + threadName);

        return AI;
    }
}
    class ArrayThread implements Runnable {
        Array5 A;
        int[] AI;
        Thread t;

        ArrayThread(String threadName, Array5 A) {
            this.A = A;

            t = new Thread(this, threadName);

            t.start();
        }

        public void run() {
            AI = A.Get(t.getName());
        }
    }
public class Main {
    public static void main(String[] args) {
        Array5 A5 = new Array5();

        ArrayThread AT1 = new ArrayThread("AT1", A5);
        ArrayThread AT2 = new ArrayThread("AT2", A5);
        ArrayThread AT3 = new ArrayThread("AT3", A5);
        
        try {
            AT1.t.join();
            AT2.t.join();
            AT3.t.join();
        }
        catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
