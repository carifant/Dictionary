package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Set;

public class Loader {
  private Connection firstConnection = new DataLoader().getFirstConnection();
  private Statement statement;
  private Map<String, Set<String>> map = new Storage().getStorage();

  public Statement getStatement() {
    try {
      statement = firstConnection.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return statement;
  }

  public void insertToFirstTable(String newWord) {
    try {

      getStatement().executeUpdate("insert into dictionary (word) values ('" + newWord + "')");
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  public void insertToSecondTable(String newWord, String engWord) {
    try {
      int id = getId(engWord);
      getStatement().executeUpdate(
            "insert into translation (translated_word, dictionary_id) values ('" + newWord +
                  "'," + id + ")");
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  public Integer getId(String newWord) {
    int temp = 0;
    try {
      ResultSet resultSet =
            getStatement().executeQuery("select id from dictionary where word = '" + newWord + "'");
      while (resultSet.next()) {
        temp = resultSet.getInt("id");
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    return temp;
  }
}
