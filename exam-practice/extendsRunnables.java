public class extendsRunnables extends runnables {
    public static void main(String[] args) {
        // runnables r1 = new runnables(); // valid

        // runnables r1 = new Runnable(runnables).start(); // no start method
        // runnables r1 = new Thread(runnables).run(); //type mismatch
        // runnables r1 = new Thread(new runnables()).start(); //type mismatch
        // runnables r1 = new runnables().start(); // no start method
    }
}
