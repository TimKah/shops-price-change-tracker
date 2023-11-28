package com.study.shop.models;

public enum Tables {
  Shops,
  Items,
  Changes;

  public String getCreateQuery() {
    switch (this) {
      case Shops -> {
        return "CREATE TABLE Shops (`ID` int NOT NULL, `NAME` VARCHAR(100) NOT NULL, PRIMARY KEY (`ID`));";
      }
      case Items -> {
        return "CREATE TABLE Items (`ID` int NOT NULL, `NAME` VARCHAR(100) NOT NULL, PRIMARY KEY (`ID`));";
      }
      case Changes -> {
        return "CREATE TABLE Changes (`SHOP_ID` int NOT NULL, `ITEM_ID` int NOT NULL, `CHANGE_DATE` DATE NOT NULL, PRIMARY KEY (`SHOP_ID`, `ITEM_ID`));";
      }
    }

    return "";
  }
}
