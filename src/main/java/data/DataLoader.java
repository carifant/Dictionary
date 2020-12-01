package data;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataLoader {

  private Connection connection;

  {
    Properties properties = new Properties();

    try (InputStream in = DataLoader.class.getClassLoader()
          .getResourceAsStream("postgress.properties")) {
      properties.load(in);
      Class.forName("org.postgresql.Driver");
      connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"
                  + properties.getProperty("dbName"), properties.getProperty("login")
            , properties.getProperty("password"));

    } catch (SQLException | IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public Connection getConnection() {
    return connection;
  }
}
