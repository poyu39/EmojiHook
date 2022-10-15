package com.poyu39.emojihook;

import com.poyu39.emojihook.config.CopyConfig;
import com.poyu39.emojihook.listener.MessageListener;
import github.scarsz.discordsrv.DiscordSRV;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class EmojiHook extends JavaPlugin implements Listener{
  public static EmojiHook emojiHook;

  @Override
  public void onEnable() {
    emojiHook = this;
    this.getLogger().info("已啟用EmojiHook");

    Objects.requireNonNull(this.getCommand("EmojiHook")).setExecutor(new Commands());
    getServer().getPluginManager().registerEvents(this, this);

    CopyConfig.copy();
    new MessageListener(this);
  }
}
