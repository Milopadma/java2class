public interface aninterfaceclass {
    // class fields
    int x = 0;
    int y = 0;
    // private int z = 0; // not valid - interface fields are always public

    // class methods
    void method1();
    void method2();

    // private void method3(); // not valid - interface methods are always public

    // void method4(){
    // System.out.println("They can have public methods?"); // not valid - interface methods are never implemented
    // }
}
