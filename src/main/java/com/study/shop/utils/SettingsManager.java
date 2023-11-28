package com.study.shop.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class SettingsManager {
  private static Properties properties = null;

  public static String getDbUrlPrefix() {
    if (properties == null) loadProperties();
    if (properties.getProperty("db.type").equals("MySQL")) return "jdbc:mysql://";

    return null;
  }

  public static String getDbUrl() {
    if (properties == null) loadProperties();
    return properties.getProperty("db.url");
  }

  public static String getDbName() {
    if (properties == null) loadProperties();
    return properties.getProperty("db.name");
  }

  public static String getDbUser() {
    if (properties == null) loadProperties();
    return properties.getProperty("db.user");
  }

  public static String getDbPassword() {
    if (properties == null) loadProperties();
    return properties.getProperty("db.password");
  }

  private static void loadProperties() {
    try {
      properties = new Properties();
      properties.load(new FileInputStream(
          Paths.get("src", "main", "resources").toFile().getAbsolutePath()
              + "/config.properties")
      );
    } catch (IOException e) {
      System.err.println("Exception in Setting Manager: " + e.getMessage());
    }
  }

}
