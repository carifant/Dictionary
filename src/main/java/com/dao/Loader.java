package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Loader {
  private static Logger logger = LoggerFactory.getLogger(Loader.class);
  private Connection firstConnection = new DataLoader().getConnection();

  public void insertToFirstTable(String newWord) {
    try {
      PreparedStatement preparedStatement =
            firstConnection.prepareStatement("insert into dictionary (word) values (?)");
      preparedStatement.setString(1, newWord);
      preparedStatement.executeUpdate();
    } catch (SQLException ex) {
      logger.error(ex.getMessage(), ex);
      ex.printStackTrace();
    }
  }

  public void insertToSecondTable(String newWord, String engWord) {
    try {
      PreparedStatement preparedStatement =
            firstConnection.prepareStatement("insert into translation " +
                  "(translated_word, dictionary_id) values (?,?)");
      preparedStatement.setString(1, newWord);
      preparedStatement.setInt(2, getId(engWord));
      preparedStatement.executeUpdate();
    } catch (SQLException ex) {
      logger.error(ex.getMessage(), ex);
      ex.printStackTrace();
    }
  }

  public Integer getId(String newWord) {
    int temp = -1;
    try {
      PreparedStatement preparedStatement =
            firstConnection.prepareStatement("select id from dictionary where word = ?");
      preparedStatement.setString(1, newWord);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        temp = resultSet.getInt("id");
      }
    } catch (SQLException ex) {
      logger.error(ex.getMessage(), ex);
      ex.printStackTrace();
    }
    return temp;
  }

  public Map<String, Set<String>> selectFromBD() {
    Map<String, Set<String>> map = new HashMap<>();
    try {
      PreparedStatement preparedStatement =
            firstConnection.prepareStatement("select word, translated_word from dictionary d" +
                  "join translation on d.id = dictionary_id");
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        String word = resultSet.getString("word");
        String translated_word = resultSet.getString("translated_word");
        Set<String> set = new HashSet<>();
        if (map.containsKey(word)) {
          map.get(word).add(translated_word);
        } else {
          set.add(translated_word);
          map.put(word, set);
        }
      }
    } catch (SQLException ex) {
      logger.error(ex.getMessage(), ex);
      ex.printStackTrace();
    }
    return map;
  }

  public void deleteWordFromFirstTable(String currentWord) {
    try {
      PreparedStatement preparedStatement =
            firstConnection.prepareStatement("delete from dictionary where word = ?");
      preparedStatement.setString(1, currentWord);
      preparedStatement.executeUpdate();
      PreparedStatement preparedStatement1 =
            firstConnection.prepareStatement("delete from translation where dictionary_id = ?");
      preparedStatement1.setInt(1, getId(currentWord));
      preparedStatement1.executeUpdate();
    } catch (SQLException ex) {
      logger.error(ex.getMessage(), ex);
      ex.printStackTrace();
    }
  }

  public void deleteWordFromSecondTable(String currentWord) {
    try {
      PreparedStatement preparedStatement =
            firstConnection.prepareStatement("delete from dictionary where word = ?");
      preparedStatement.setString(1, currentWord);
      preparedStatement.executeUpdate();
    } catch (SQLException ex) {
      logger.error(ex.getMessage(), ex);
      ex.printStackTrace();
    }
  }
}


