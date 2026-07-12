package com.tgbot.prodaction;

import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
    String text = "/reminder  19:30 13.06.26 batle cup";
    String[] text_ = text.trim().split("\\s+");
    for (int i = 0; i < text_.length; i++) {
      System.out.println((i + 1) + " - " + text_[i]);
    }
  }
}
