package mainPackage;

import data.Storage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;

public class QuizGame {

  public void quizGame() {
    try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
      Map<String, Set<String>> storage = new Storage().getStorage();
      int count = 0;
      System.out.println("Игра начата.");
      System.out.println("Для выхода из игры введите Exit");
      for (String x : storage.keySet()) {
        System.out.println("Переведите данное слово: " + x);
        String temp = bufferedReader.readLine();
        if (temp.equals("Exit")) {
          break;
        }
        if (storage.get(x).contains(temp)) {
          System.out.println("Перевод верный");
        } else {
          System.out.println("Перевод неверный. Правильный ответ: " + storage.get(x));
          count++;
        }
      }
      System.out.println("Игра окончена, дано неверных ответов: " + count);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

}
