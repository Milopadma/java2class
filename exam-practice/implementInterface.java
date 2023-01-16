public class implementInterface implements aninterfaceclass {
    // must implement all methods of the interface
    public void method1() {
        System.out.println("Method 1");
    }

    public void method2() {
        System.out.println("Method 2");
    }

    public static void main(String[] args) {
        implementInterface i = new implementInterface();
        i.method1();
        i.method2();
    }
}
