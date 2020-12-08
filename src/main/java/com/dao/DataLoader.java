package com.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataLoader {

  private static Logger logger = LoggerFactory.getLogger(DataLoader.class);
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
      logger.error(e.getMessage(), e);
      e.printStackTrace();
    }
  }

  public Connection getConnection() {
    return connection;
  }
}
