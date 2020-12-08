package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Loader {
  private static Logger logger = LoggerFactory.getLogger(Loader.class);
  private Connection firstConnection = new DataLoader().getConnection();
  private Statement statement;

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
      logger.error(ex.getMessage(), ex);
      ex.printStackTrace();
    }
  }

  public void insertToSecondTable(String newWord, String engWord) {
    try {
      getStatement().executeUpdate(
            "insert into translation (translated_word, dictionary_id) values ('" + newWord +
                  "'," + getId(engWord) + ")");
    } catch (SQLException ex) {
      logger.error(ex.getMessage(), ex);
      ex.printStackTrace();
    }
  }

  public Integer getId(String newWord) {
    int temp = -1;
    try {
      ResultSet resultSet =
            getStatement().executeQuery("select id from dictionary where word = '" + newWord + "'");
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
      ResultSet resultSet =
            getStatement().executeQuery("select word, translated_word from dictionary d " +
                  "join translation on d.id = dictionary_id");
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

  public void deleteWordFromFirstTable (String currentWord){
    try {
      getStatement().executeUpdate("delete  from dictionary where word = '" + currentWord + "'");
      getStatement().executeUpdate("delete  from translation where dictionary_id = " + getId(currentWord));
    } catch (SQLException ex) {
      logger.error(ex.getMessage(), ex);
      ex.printStackTrace();
    }
  }

  public void deleteWordFromSecondTable (String currentWord){
    try {
      getStatement().executeUpdate("delete word from translation where translated_word = '" + currentWord + "'");
    } catch (SQLException ex) {
      logger.error(ex.getMessage(), ex);
      ex.printStackTrace();
    }
  }
  }


