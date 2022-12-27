package github.io.sweetenpotato.customnickname;

import github.io.sweetenpotato.customnickname.commands.CustomNicknameCommand;
import github.io.sweetenpotato.customnickname.yml.ConfigYml;
import github.io.sweetenpotato.customnickname.yml.NicknameYml;
import org.bukkit.ChatColor;
import org.bukkit.Instrument;
import org.bukkit.Note;
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
        getCommand("customnickname").setExecutor(new CustomNicknameCommand(this));
    }

    @Override
    public void onDisable() {
        NicknameYml.saveCustomNicknameYml();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (NicknameYml.doesPlayerHaveCustomNickname(player)) {
            String customNickname = NicknameYml.getCustomNickname(player);
            player.setDisplayName(customNickname);
            player.setPlayerListName(customNickname);
        }
        else {
            player.sendMessage(
                    String.format(ChatColor.WHITE + "[" + "%s" + ChatColor.WHITE + "] 이 서버에서 사용할 닉네임을 선정할 수 있습니다!", this.getConfig().getString("chat-prefix.info"))
            );
            player.playNote(player.getLocation(), Instrument.BELL, Note.natural(1, Note.Tone.G));
        }
    }
}
