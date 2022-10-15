package com.poyu39.emojihook.config;

import com.poyu39.emojihook.EmojiHook;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CopyConfig {
  public static void copy() {
    Path path = Paths.get("/Emoji.yml");
    if (Files.notExists(path)) {
      EmojiHook.emojiHook.saveResource("Emoji.yml", false);
    }
  }
}
