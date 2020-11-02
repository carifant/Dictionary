package data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Storage {

  private static Map<String, Set<String>> storage = new HashMap<>();

  public void addWord(String eng, String rus) {
    Set<String> set = new HashSet<>();
    if (storage.containsKey(eng)) {
      set = storage.get(eng);
      set.add(rus);
    } else {
      set.add(rus);
      storage.put(eng, set);
    }
  }
}
