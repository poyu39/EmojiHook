package com.poyu39.emojihook;

import org.bukkit.plugin.java.JavaPlugin;

public final class EmojiHook extends JavaPlugin{
  @Override
  public void onEnable() {
    this.getLogger().info("已啟用EmojiHook");
    this.getCommand("EmojiHook").setExecutor(new Commands());
    new MessageListener(this);
  }


}
