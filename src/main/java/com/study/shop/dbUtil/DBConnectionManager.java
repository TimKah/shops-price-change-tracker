package com.study.shop.dbUtil;

import com.study.shop.utils.SettingsManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {
  private static DBConnectionManager instance;

  public static DBConnectionManager getInstance() {
    if (instance == null) instance = new DBConnectionManager();
    return instance;
  }

  public Connection connect() throws SQLException {
    return DriverManager.getConnection(
        String.format("%s%s/%s?createDatabaseIfNotExist=true&autoReconnect=true&useSSL=false",
            SettingsManager.getDbUrlPrefix(),
            SettingsManager.getDbUrl(),
            SettingsManager.getDbName()),
        SettingsManager.getDbUser(),
        SettingsManager.getDbPassword());
  }
}
