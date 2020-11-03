package mainPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {

  private String[] arr = new String[5];

  private void setOfArr() {
    arr[0] = "Добро пожаловать в программу изучения слов Английского языка!";
    arr[1] = "Для выбора режима работы, введите номер пункта меню:";
    arr[2] = "1) работа со словарем (удаление, редактирование, добавление слов)";
    arr[3] = "2) проверка своих знаний, необходимо переводить выдаваемые программой слова";
    arr[4] = "3) выйти из программы.";
  }

  public void chooser() {
    try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
      String temp = bufferedReader.readLine();
      if (Integer.parseInt(temp) == 1) {
        new FirstStage().starter();
      } else if (Integer.parseInt(temp) == 2) {
        new SecondStage().starter();
      } else if (Integer.parseInt(temp) == 3) {
        System.out.println("Программа завершена");
        System.exit(0);
      }
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public void mainMethod() {
    setOfArr();
    for (int i = 0; i < arr.length; i++) {
      System.out.println(arr[i]);
    }
    chooser();
  }

}
