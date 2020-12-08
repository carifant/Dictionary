package com.mainPackage;

import com.dao.DataLoader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecondStage {

  private static Logger logger = LoggerFactory.getLogger(DataLoader.class);
  private String[] arr = new String[3];
  BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
    try  {
      String temp = reader.readLine();
      if (Integer.parseInt(temp) == 1) {
        new QuizGame().quizGame();
        System.exit(0);
      } else if (Integer.parseInt(temp) == 2) {
        new Console().mainMethod();
      }
    } catch (IOException ex) {
      logger.error(ex.getMessage(), ex);
      ex.printStackTrace();
    }
  }

  public void starter() {
    setMethod();
    print();
    chooser();
  }
}

