package com.poyu39.emojihook.listener;

import com.poyu39.emojihook.EmojiHook;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class MessageListener implements Listener {

  public Map<String, String> emojiList = new HashMap<String, String>();

  public MessageListener(EmojiHook plugin) {
    plugin.getServer().getPluginManager().registerEvents(this, plugin);

    File file = new File(EmojiHook.emojiHook.getDataFolder(), "/Emoji.yml");
    FileConfiguration readEmoji = YamlConfiguration.loadConfiguration(file);
    for (String key : readEmoji.getConfigurationSection("EmojiList").getKeys(false)) {
      emojiList.put(key, readEmoji.getString("EmojiList." + key + ".Unicode"));
    }
  }

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    String playerName = event.getPlayer().getName();
    event.setJoinMessage("歡迎 " + playerName + " 進入到伺服器!");
  }

  @EventHandler
  public void onPlayerMessage(PlayerChatEvent event) {
    String getMessage = event.getMessage();
    int firstIndex = getMessage.indexOf(":");
    if (firstIndex != -1) {
      int lastIndex = getMessage.indexOf(":", firstIndex + 1);
      if (lastIndex != firstIndex) {
        convertToUnicode(event, getMessage, firstIndex, lastIndex);
      }
    }
  }
  public void convertToUnicode(PlayerChatEvent event, String rawMessage, int firstIndex, int lastIndex) {
    String keyWord = rawMessage.substring(firstIndex, lastIndex + 1);
    String reKeyWord = "";
    boolean keyExist = emojiList.containsKey(keyWord);
    if (keyExist) {
      reKeyWord = emojiList.get(keyWord);
      rawMessage = rawMessage.replace(keyWord, reKeyWord);
      System.out.println("have key word");
    }
    event.setMessage(rawMessage);
  }
}
