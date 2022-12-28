package github.io.sweetenpotato.customnickname;

import github.io.sweetenpotato.customnickname.commands.CustomNicknameCommand;
import github.io.sweetenpotato.customnickname.listener.PlayerDeathListener;
import github.io.sweetenpotato.customnickname.listener.PlayerJoinListener;
import github.io.sweetenpotato.customnickname.yml.ConfigYml;
import github.io.sweetenpotato.customnickname.yml.NicknameYml;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.TranslatableComponent;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        new NicknameYml(this);
        new ConfigYml(this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getCommand("customnickname").setExecutor(new CustomNicknameCommand(this));
    }

    @Override
    public void onDisable() {
        NicknameYml.saveCustomNicknameYml();
    }
}
