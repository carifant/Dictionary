package mainPackage;

import data.Storage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;

public class QuizGame {
  BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


  public void quizGame() {
    System.out.println("Игра начата.");
    System.out.println("Для выхода из игры введите Exit");
    int count = loopMethod();
    System.out.println("Игра окончена, дано неверных ответов: " + count);
  }

  public int loopMethod() {
    int count = 0;
    try {
      Map<String, Set<String>> storage = new Storage().getStorage();
      for (String x : storage.keySet()) {
        System.out.println("Переведите данное слово: " + x);
        String line = bufferedReader.readLine();
        if (line.equalsIgnoreCase("Exit")) {
          break;
        }
        if (storage.get(x).contains(line)) {
          System.out.println("Перевод верный");
        } else {
          System.out.println("Перевод неверный. Правильный ответ: " + storage.get(x));
          count++;
        }
      }

    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return count;
  }
}

