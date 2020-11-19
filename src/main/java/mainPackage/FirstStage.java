package mainPackage;

import data.Loader;
import data.Storage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class FirstStage {
  private String[] arr = new String[5];

  private void setMethod() {
    arr[0] = "Выберите желаемое действие:";
    arr[1] = "1) Добавить новое слово.";
    arr[2] = "2) Удалить слово.";
    arr[3] = "3) Перевести слово.";
    arr[4] = "4) Выход в предыдущее меню.";
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
        System.out.println("Введите в первой строчке английское слово, во второй перевод. " +
              "В перевод можно добавить только одно значение. " +
              "Остальные варианты  перевода добавляются повторным вызовом данного пункта.");
        String tempEng = bufferedReader.readLine().toLowerCase();
        String tempRus = bufferedReader.readLine().toLowerCase();
        new Storage().addWord(tempEng, tempRus);
        new Loader().insertToFirstTable(tempEng);
        new Loader().insertToSecondTable(tempRus, tempEng);
        System.out.println("Слово добавлено в словарь");
      } else if (Integer.parseInt(temp) == 2) {
        System.out.println("Введите слово, которое хотите удалить из словаря");
        String tempEng = bufferedReader.readLine().toLowerCase();
        if (new Storage().getStorage().containsKey(tempEng)) {
          new Storage().deleteWord(tempEng);
          System.out.println("Слово успешно удалено.");
        } else {
          System.out.println("Данное слово не обнаружено");
        }
      } else if (Integer.parseInt(temp) == 3) {
        System.out
              .println("Введите английское слово, если оно есть в словаре, вы получите перевод");
        String tempEng = bufferedReader.readLine().toLowerCase();
        System.out.println(new Storage().getTranslate(tempEng));
      } else if (Integer.parseInt(temp) == 4) {
        System.out.println("Выход из меню");
        new Console().mainMethod();
      }
      print();
      chooser();
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
