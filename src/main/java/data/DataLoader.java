package data;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataLoader {

  private Connection firstConnection;
  private String tableName1;
  private String login;
  private String password;



  {
    Properties properties = new Properties();

    try(InputStream in = DataLoader.class.getClassLoader().getResourceAsStream("postgress.properties")) {
      properties.load(in);
      tableName1 = properties.getProperty("tableName1");
      Class.forName("org.postgresql.Driver");
      login = properties.getProperty("login");
      password = properties.getProperty("password");
      firstConnection =
            DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + tableName1,
                  login, password);


    } catch (SQLException | IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public Connection getFirstConnection() {
    return firstConnection;
  }
}
