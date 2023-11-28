package com.study.shop.dbUtil;

public class SQLFormatter {
  public static String getDropDatabaseQuery(String dbName) {
    return String.format("DROP DATABASE %s;", dbName);
  }

  public static String getInsertQuery(String dbName, String table, String[] fields, String[] values) {
    StringBuilder query = new StringBuilder();
    query.append("INSERT INTO `");
    query.append(dbName).append("`.`").append(table).append("` (");
    String prefix = "";
    for (String field : fields) {
      query.append(prefix);
      prefix = ", ";
      query.append("`").append(field).append("`");
    }
    query.append(") VALUES (");
    prefix = "";
    for (String value : values) {
      query.append(prefix);
      prefix = ", ";
      query.append("'").append(value).append("'");
    }
    query.append(");");

    return query.toString();
  }
}
