package textproc;

import java.util.*;

public class MultiWordCounter implements TextProcessor{
  private Map<String, Integer> map;

  public MultiWordCounter(String[] vector) {
    this.map = new HashMap<String, Integer>();

    for (String word : vector) {
      map.put(word, 0);
    }
  }

  public void process(String w) {
    if (map.containsKey(w)) {
      map.put(w, map.get(w) + 1);
    }
  }

  public void report() {
    for (String word : map.keySet()) {
      System.out.print(word + ": " + map.get(word) + ", ");
    }
    System.out.print('\n');
  }
}
