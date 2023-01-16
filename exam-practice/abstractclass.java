public abstract class abstractclass implements java.io.Serializable, Runnable, aninterfaceclass { // can implement
                                                                                                  // interfaces
    // abstract fields
    private int x;
    private int y;

    // private abstract int z; // not valid

    public abstract void abstractmethod();

    public void test() {
        System.out.println("They can have public methods");
    }
}

class normalClass extends abstractclass {
    // must implement all abstract methods
    // these methods are inherited from abstractclass
    public void abstractmethod() {
        System.out.println("This is an abstract method");
    }

    // these methods are inherited from Runnable
    public void run() {
        System.out.println("This is a runnable method");
    }

    // these methods are inherited from aninterfaceclass
    @Override
    public void method1() {
        // TODO Auto-generated method stub

    }

    @Override
    public void method2() {
        // TODO Auto-generated method stub

    }
}
