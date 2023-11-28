package com.study.shop;

import com.study.shop.dbUtil.DBHandler;
import com.study.shop.dbUtil.MySQLDBHandler;
import com.study.shop.models.Tables;

public class Main {

  public static void main(String[] args) {
    DBHandler dbHandler = new MySQLDBHandler();

    dbHandler.drop();
    dbHandler.create();
    dbHandler.populateData(Tables.Shops);
    dbHandler.populateData(Tables.Items);
    dbHandler.populateData(Tables.Changes);
    dbHandler.printShopsChanges();
  }
}