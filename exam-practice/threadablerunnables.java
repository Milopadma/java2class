public class threadablerunnables extends Thread {
    public static void main(String[] args) {
        Thread t1 = new Thread();
        Thread t2 = new Thread();
        t1.start();
        System.out.println("t1 started");
        t2.start();
        System.out.println("t2 started");
    }

    public void run() {
        System.out.println("Thread is running");
    }

}
