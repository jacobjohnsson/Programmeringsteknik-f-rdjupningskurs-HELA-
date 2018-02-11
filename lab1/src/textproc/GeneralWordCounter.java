package textproc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class GeneralWordCounter implements TextProcessor {
  private Map<String, Integer> counts;
  private Set<String> stopwords;

  public GeneralWordCounter(Set<String> excludeSet) {
    counts = new HashMap<String, Integer>();
    stopwords = excludeSet;
  }
  
  public Set<Map.Entry<String, Integer>> getWords() {
	  return counts.entrySet();
  }

  public void process(String word) {
  	if (counts.containsKey(word) && !stopwords.contains(word)) {
      counts.put(word, counts.get(word) + 1);
  	} else if (!stopwords.contains(word)){
      counts.put(word, 1);
  	}
  }

  public void report() {
    Set<Map.Entry<String,Integer>> wordSet = counts.entrySet();
    List<Map.Entry<String,Integer>> wordList = new ArrayList<>(wordSet);
    wordList.sort(new WordCountComparator());

    for (int i = 0; i < wordList.size() ; i++) {
      System.out.print(wordList.get(i).getKey() + ": " + wordList.get(i).getValue() + ", ");
    }
    System.out.print('\n');
  }
}
