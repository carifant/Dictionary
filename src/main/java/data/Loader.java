package data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Set;

public class Loader {
  private Connection firstConnection = new DataLoader().getFirstConnection();
  private Connection secondConnection = new DataLoader().getSecondConnection();
  private Map<String, Set<String>> map = new Storage().getStorage();

  public void insertToFirstTable(){
    try {
      Statement statement = firstConnection.createStatement();
      statement.executeUpdate("insert into dictionary (word) values ('one')");
    }catch (SQLException ex){
      ex.printStackTrace();
    }
  }

  public static void main(String[] args) {
    new Loader().insertToFirstTable();
  }
}
