import java.util.*;

public class Övn {
  public static void main(String[] args) {
    List<Map.Entry<Object, Integer>> myList = new ArrayList<Map.Entry<Object, Integer>>();

    myList.add(new AbstractMap.SimpleEntry<Object, Integer>("Ett", 1));
    myList.add(new AbstractMap.SimpleEntry<Object, Integer>("Tre", 3));
    myList.add(new AbstractMap.SimpleEntry<Object, Integer>("Två", 2));

    myList.sort((e1, e2) -> e2.getValue() - e1.getValue());

    System.out.println(myList);
  }
}
