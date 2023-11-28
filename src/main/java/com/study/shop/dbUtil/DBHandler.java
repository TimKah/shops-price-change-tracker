package com.study.shop.dbUtil;

import com.study.shop.models.Tables;

public interface DBHandler {
  void drop();
  void create();
  void populateData(Tables table);
  void printShopsChanges();
}
