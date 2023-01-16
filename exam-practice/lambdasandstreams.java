import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Stream;

public class lambdasandstreams {
    // this class shows the example snippet codes for lambdas and streams

    // class fields
    private int x;
    private ArrayList<String> arrayList = new ArrayList<String>();

    // class constructor
    public lambdasandstreams(int x) {
        this.x = x;
        arrayList.add("element1");
        arrayList.add("element2");
        arrayList.add("element3");
    }

    public static void main(String[] args) {
        lambdasandstreams l = new lambdasandstreams(1);

        // streams example
        Stream.of(l.arrayList).forEach(System.out::println);

        // streams sorting example
        Stream.of(l.arrayList)
                // sorted alphabetically
                .sorted()
                .forEach(System.out::println);

        // streams anyMatch example
        // checks if any element matches the condition
        boolean containsE = Stream.of(l.arrayList)
                .anyMatch((s) -> s.contains("e"));

        // lambdas example
        // (parameters) -> statement
        l.arrayList.forEach((n) -> System.out.println(n));

    }

}
