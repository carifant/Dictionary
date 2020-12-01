package data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Storage {

  private static Map<String, Set<String>> storage = new HashMap<>();

  public void deleteWord(String eng) {
    new Loader().deleteWordFromFirstTable(eng);
  }

  public Map<String, Set<String>> getStorage() {
    storage = new Loader().selectFromBD();
    return storage;
  }

  public Set<String> getTranslate(String eng) {
    if (storage.containsKey(eng)) {
      return storage.get(eng);
    } else {
      return new HashSet<>();
    }
  }
}
