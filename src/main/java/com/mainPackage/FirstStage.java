package com.mainPackage;

import com.dao.DataLoader;
import com.dao.Loader;
import com.dao.Storage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FirstStage {
  private static Logger logger = LoggerFactory.getLogger(DataLoader.class);
  private String[] arr = new String[5];
  BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

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
    try{
      String temp = reader.readLine();
      if (Integer.parseInt(temp) == 1) {
        firstStep();
      } else if (Integer.parseInt(temp) == 2) {
        secondStep();
      } else if (Integer.parseInt(temp) == 3) {
        thirdStep();
      } else if (Integer.parseInt(temp) == 4) {
        System.out.println("Выход из меню");
        new Console().mainMethod();
      }
      print();
      chooser();

    } catch (IOException ex) {
      logger.error(ex.getMessage(), ex);
      ex.printStackTrace();
    }
  }

  private void firstStep() {
    try {
      System.out.println("Введите в первой строчке английское слово, во второй перевод. " +
            "В перевод можно добавить только одно значение. " +
            "Остальные варианты  перевода добавляются повторным вызовом данного пункта.");
      String tempEng = reader.readLine().toLowerCase();
      String tempRus = reader.readLine().toLowerCase();
      new Loader().insertToFirstTable(tempEng);
      new Loader().insertToSecondTable(tempRus, tempEng);
      System.out.println("Слово добавлено в словарь");
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
      e.printStackTrace();
    }
  }

  private void secondStep() {
    try  {
      System.out.println("Введите слово, которое хотите удалить из словаря");
      String tempEng = reader.readLine().toLowerCase();
      if (new Storage().getStorage().containsKey(tempEng)) {
        new Storage().deleteWord(tempEng);
        System.out.println("Слово успешно удалено.");
      } else {
        System.out.println("Данное слово не обнаружено");
      }
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
      e.printStackTrace();
    }
  }

  private void thirdStep() {
    try {
      System.out
            .println("Введите английское слово, если оно есть в словаре, вы получите перевод");
      String tempEng = reader.readLine().toLowerCase();
      System.out.println(new Storage().getTranslate(tempEng));
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
      e.printStackTrace();
    }
  }

    public void starter() {
    setMethod();
    print();
    chooser();
  }
}
