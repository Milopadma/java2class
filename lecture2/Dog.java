package lecture2;

public class Dog {

    // class variables
    private String name;
    private int age;

    // constructor
    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // toString
    public String toString() {
        return "Dog [name = " + name + ", age = " + age + "]";
    }

}
