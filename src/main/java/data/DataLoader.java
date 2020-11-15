package data;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataLoader {

  private Connection connection;
  private String tableName;
  private String login;
  private String password;



  {
    Properties properties = new Properties();

    try(InputStream in = DataLoader.class.getClassLoader().getResourceAsStream("postgress.properties")) {
      properties.load(in);
      tableName = properties.getProperty("tableName1");
      login = properties.getProperty("login");
      password = properties.getProperty("password");
      connection =
            DriverManager.getConnection("jdbc:postgresql://localhost:5432/"
                  + tableName + ", " + login + ", "  + password);
    } catch (SQLException | IOException e) {
      e.printStackTrace();
    }
  }

}
