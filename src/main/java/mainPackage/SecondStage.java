package mainPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SecondStage {

  private String[] arr = new String[3];

  private void setMethod() {
    arr[0] = "Выберите желаемое действие:";
    arr[1] = "1) Запустить проверку ваших знаний.";
    arr[2] = "2) Выход в предыдущее меню.";
  }

  private void print() {
    for (String s : arr) {
      System.out.println(s);
    }
  }

  private void chooser() {
    try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
      String temp = bufferedReader.readLine();
      if (Integer.parseInt(temp) == 1) {
        new QuizGame().quizGame();
      } else if (Integer.parseInt(temp) == 2) {
        new Console().mainMethod();
      }
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public void starter() {
    setMethod();
    print();
    chooser();
  }
}

