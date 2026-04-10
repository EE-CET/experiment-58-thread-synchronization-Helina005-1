



// Shared resource class
class Table {
    // The synchronized keyword ensures no two threads can execute this method simultaneously on the same object
    synchronized void printTable(int n) {
        for (int i = 1; i <= 5; i++) {
            // Print the multiples space-separated
            if (i == 5) {
                System.out.print(n * i); // No trailing space for the last number
            } else {
                System.out.print((n * i) + " ");
            }
        }
        // Move to the next line after the table is complete
        System.out.println();
    }
}

// Thread class
class MyThread extends Thread {
    Table t;
    int n;

    // Constructor to pass the shared Table object and the number to print
    MyThread(Table t, int n) {
        this.t = t;
        this.n = n;
    }

    @Override
    public void run() {
        t.printTable(n);
    }
}

public class SynchronizationDemo {
    public static void main(String[] args) {
        // Create the single shared Table object
        Table obj = new Table();

        // Create two threads, sharing the SAME Table object
        MyThread t1 = new MyThread(obj, 5);
        MyThread t2 = new MyThread(obj, 100);

        // Start both threads
        // Because printTable is synchronized, their outputs won't interleave
        t1.start();
        t2.start();
    }
}