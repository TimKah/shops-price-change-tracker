package com.study.shop.dbUtil;

import com.study.shop.models.Tables;
import com.study.shop.utils.DataLoader;
import com.study.shop.utils.SettingsManager;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class MySQLDBHandler implements DBHandler {

  @Override
  public void drop() {
    DBConnectionManager connectionManager = DBConnectionManager.getInstance();
    try (Connection conn = connectionManager.connect()) {
      conn.prepareStatement(
          SQLFormatter.getDropDatabaseQuery(SettingsManager.getDbName())
      ).execute();
    } catch (SQLException e) {
      System.err.println("Exception in DB Handler while trying to drop: " + e.getMessage());
    }
  }

  @Override
  public void create() {
    try (Connection conn = DBConnectionManager.getInstance().connect()) {
      conn.prepareStatement(Tables.Shops.getCreateQuery()).execute();
      conn.prepareStatement(Tables.Items.getCreateQuery()).execute();
      conn.prepareStatement(Tables.Changes.getCreateQuery()).execute();
    } catch (SQLException e) {
      System.err.println("Exception in DB Handler while trying to create: " + e.getMessage());
    }
  }

  @Override
  public void populateData(Tables table) {
    if (table == null)
      return;

    List<String> data = DataLoader.getFileText(table.toString());

    try (Connection conn = DBConnectionManager.getInstance().connect()) {
      Iterator<String> iterator = data.iterator();

      String[] fields = iterator.next().split("\t");

      while (iterator.hasNext()) {
        String[] values = iterator.next().split("\t");

        conn.prepareStatement(
            SQLFormatter.getInsertQuery(SettingsManager.getDbName(), table.toString(), fields, values)
        ).execute();
      }
    } catch (SQLException e) {
      System.err.println("Exception in DB Handler while trying to populate: " + e.getMessage());
    } catch (NoSuchElementException e) {
      System.err.printf("Exception in DB Handler while trying to populate: file %s is empty%n", table);
    }
  }

  @Override
  public void printShopsChanges() {
    System.out.println("Shops changes:");

    try (Connection conn = DBConnectionManager.getInstance().connect()) {
      Statement statement = conn.createStatement();
      ResultSet resultSet = statement.executeQuery(
          "SELECT s.NAME AS \"SHOP\", i.NAME as \"ITEM\", c.CHANGE_DATE \n"
              + "FROM changes c\n"
              + "LEFT JOIN shops s\n"
              + "ON c.SHOP_ID = s.ID\n"
              + "LEFT JOIN items i\n"
              + "ON c.ITEM_ID = i.ID"
      );

      while (resultSet.next()) {
        String shop = resultSet.getString("SHOP");
        String item = resultSet.getString("ITEM");
        Date date = resultSet.getDate("CHANGE_DATE");

        System.out.printf("\t%s will change price for %s at %s%n", shop, item, date.toString());
      }
    } catch (Exception e) {
      System.err.println("Exception in DB Handler while trying to print changes: " + e.getMessage());
    }
  }
}
