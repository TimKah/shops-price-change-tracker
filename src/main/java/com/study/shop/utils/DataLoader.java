package com.study.shop.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class DataLoader {
  public static List<String> getFileText(String name) {
    List<String> data = new LinkedList<>();

    String path = Paths.get("src", "main", "resources").toFile().getAbsolutePath();
    path += "\\Data\\" + name + ".txt";

    try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
      while (reader.ready()) {
        data.add(reader.readLine());
      }
    } catch (IOException e) {
      System.err.println("Exception in DataLoader: " + e.getMessage());
    }

    return data;
  }
}
