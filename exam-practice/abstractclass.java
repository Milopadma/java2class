public abstract class abstractclass implements java.io.Serializable, Runnable {
    // abstract fields
    private int x;
    private int y;

    // private abstract int z; // not valid

    public abstract void abstractmethod();

    public void test() {
        System.out.println("They can have public methods?");
    }
}
