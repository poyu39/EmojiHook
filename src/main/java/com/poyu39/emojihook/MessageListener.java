package com.poyu39.emojihook;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class MessageListener implements Listener {
  public MessageListener(EmojiHook plugin) {
    plugin.getServer().getPluginManager().registerEvents(this, plugin);
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
    // System.out.println(getMessage);
    if (firstIndex != -1) {
      int lastIndex = getMessage.indexOf(":", firstIndex + 1);
      // System.out.println(firstIndex);
      // System.out.println(lastIndex);
      if (lastIndex != firstIndex) {
        // System.out.println("have key word");
        switchToUnicode(event, getMessage, firstIndex, lastIndex);
      }
    }
  }
  public void switchToUnicode(PlayerChatEvent event, String rawMessage, int firstIndex, int lastIndex) {
    String keyWord = rawMessage.substring(firstIndex, lastIndex + 1);
    String reKeyWord = "";
    // System.out.println(keyWord);
    switch (keyWord) {
      case ":bird_sign:":
        reKeyWord = "퀁";
        break;

      case ":eat_popcorn:":
        reKeyWord = "퀂";
        break;
    }
    rawMessage = rawMessage.replace(keyWord, reKeyWord);
    event.setMessage(rawMessage);
  }
}
