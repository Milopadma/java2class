import java.util.*;
import java.util.stream.Stream;

public class collections {
    // implemented lists, sets and maps
    private ArrayList<String> arrayList = new ArrayList<String>();
    private HashSet<String> hashSet = new HashSet<String>();
    private HashMap<String, String> hashMap = new HashMap<String, String>();

    // normal list
    // private List<String> list = new List<String>() {
    // multiple "must implement the inherited abstract method"
    // @Override
    // public boolean add(String e) {
    // return false;
    // }
    // };

    // class constructor
    public collections() {
        // add elements to the lists, sets and maps
        arrayList.add("element1");
        arrayList.add("element2");
        arrayList.add("element3");

        hashSet.add("element1");
        hashSet.add("element2");
        hashSet.add("element3");

        hashMap.put("key1", "element1");
        hashMap.put("key2", "element2");
        hashMap.put("key3", "element3");

        // print the lists, sets and maps
        Stream.of(arrayList).forEach(System.out::println);
        Stream.of(hashSet).forEach(System.out::println);
        Stream.of(hashMap).forEach(System.out::println);
    }

    public static void main(String[] args) {
        collections c = new collections();
    }
}
