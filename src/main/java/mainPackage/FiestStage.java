package mainPackage;

import data.Storage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FiestStage {
  private String[] arr = new String[5];

  private void setMethod(){
    arr[0] = "Выберите желаемое действие:";
    arr[1] = "1) Добавить новое слово.";
    arr[2] = "2) Удалить слово.";
    arr[3] = "3) Редактировать слово.";
    arr[4] = "4) Выйти из данного режима.";
  }

  private void print() {
    for (String s: arr) {
      System.out.println(s);
    }
  }

  private void chooser() {
    try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
      String temp = bufferedReader.readLine();
      if (Integer.parseInt(temp) == 1) {
        System.out.println("ВВедите в первой строчке английское слово, во второй перевод. " +
              "В перевод можно добавить только одно значение. " +
              "Остальные варианты  перевода добавляются повторным вызовом данного пункта.");
        String tempEng = bufferedReader.readLine();
        String tempRus = bufferedReader.readLine();
        new Storage().addWord(tempEng,tempRus);
      } else if (Integer.parseInt(temp) == 2) {
        System.out.println("Второй режим");
      } else if(Integer.parseInt(temp) == 3) {
        System.out.println("Программа завершена");
        System.exit(0);
      }
    } catch ( IOException ex) {
      ex.printStackTrace();
    }
  }

  public void starter(){
    setMethod();
    print();
    chooser();
  }
}
