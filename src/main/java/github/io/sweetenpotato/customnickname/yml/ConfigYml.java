package github.io.sweetenpotato.customnickname.yml;

import github.io.sweetenpotato.customnickname.Main;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigYml {

    private FileConfiguration config;

    public ConfigYml(Main plugin) {
        this.config = plugin.getConfig();
        plugin.saveDefaultConfig();
    }
}
