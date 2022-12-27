package github.io.sweetenpotato.customnickname.yml;

import github.io.sweetenpotato.customnickname.Main;
import org.apache.commons.io.FileUtils;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NicknameYml {

    static HashMap<OfflinePlayer, String> customNicknameMap;
    static File customNicknameFile;
    static FileConfiguration config;

    public NicknameYml(Main plugin) {
        File customNicknameDirectory = new File(plugin.getDataFolder(), "/");
        customNicknameFile = new File(customNicknameDirectory, "nickname.yml");
        config = YamlConfiguration.loadConfiguration(customNicknameFile);
        if (!customNicknameDirectory.isDirectory()) {
            customNicknameDirectory.mkdir();
            try {
                InputStream stream = plugin.getResource("nickname.yml");
                FileUtils.copyInputStreamToFile(stream, customNicknameFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileConfiguration customNicknameConfig = YamlConfiguration.loadConfiguration(customNicknameFile);
        for (String uuid : customNicknameConfig.getKeys(false)) {
            customNicknameMap.put(plugin.getServer().getOfflinePlayer(UUID.fromString(uuid)), customNicknameConfig.getString(uuid));
        }
    }

    public static String getCustomNickname(OfflinePlayer player) {
        return customNicknameMap.get(player);
    }

    public static boolean doesPlayerHaveCustomNickname(Player player) {
        return customNicknameMap.containsKey(player);
    }

    public static boolean doesCustomNicknameExist(String nickname) { return customNicknameMap.containsValue(nickname); }

    public static void setCustomNickname(OfflinePlayer player, String nickname) {
        customNicknameMap.put(player, nickname);
    }

    public static void delCustomNickname(OfflinePlayer player) {
        customNicknameMap.remove(player);
    }

    public static void saveCustomNicknameYml() {
        for(Map.Entry<OfflinePlayer, String> entry : customNicknameMap.entrySet()) {
            config.set(entry.getKey().getUniqueId().toString(), entry.getValue());
        }
        try {
            config.save(customNicknameFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
