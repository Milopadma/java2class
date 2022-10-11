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

    // other methods
    // to person years methods
    // since 1 dog year is equivalent to 7 human years
    public int toPersonYears() {
        return age * 7;
    }

    // toString
    public String toString() {
        return "Dog [name = " + name + ", age = " + age + " with human years of " + toPersonYears() + "]";
    }

}

// DogTest class (Kennel?)

class Kennel {
    public static void main(String[] args) {
        Dog dog1 = new Dog("Roger", 2);
        Dog dog2 = new Dog("Rex", 50);
        Dog dog3 = new Dog("Rover", 7);

        System.out.println(dog1);
        System.out.println(dog2);
        System.out.println(dog3);
    }
}
