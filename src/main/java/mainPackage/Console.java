package mainPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {

  private String[] arr = new String[5];

  private BufferedReader getBufferedReader(){
    return new BufferedReader(new InputStreamReader(System.in));
  }

  private void setOfArr() {
    arr[0] = "Добро пожаловать в программу изучения слов Английского языка!";
    arr[1] = "Для выбора режима работы, введите номер пункта меню:";
    arr[2] = "1) работа со словарем (удаление, редактирование, добавление слов)";
    arr[3] = "2) проверка своих знаний, необходимо переводить выдаваемые программой слова";
    arr[4] = "3) выйти из программы.";
  }

  public void chooser() throws IOException {
        String temp = getBufferedReader().readLine();
        if (Integer.parseInt(temp) == 1) {
          new FirstStage().starter();
        } else if (Integer.parseInt(temp) == 2) {
          new SecondStage().starter();
        } else if (Integer.parseInt(temp) == 3) {
          System.out.println("Программа завершена");
          System.exit(0);
      }
  }

  public void mainMethod() {
      setOfArr();
      for (String s : arr) {
        System.out.println(s);
      }
    try {
      chooser();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  }

