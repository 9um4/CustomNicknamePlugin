package github.io.sweetenpotato.customnickname.listener;

import github.io.sweetenpotato.customnickname.Main;
import github.io.sweetenpotato.customnickname.yml.NicknameYml;
import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.chat.hover.content.Content;
import net.md_5.bungee.api.chat.hover.content.Text;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private String infoPrefix;
    private String errorPrefix;

    public PlayerJoinListener(Main plugin) {
        this.infoPrefix = plugin.getConfig().getString("chat-prefix.info");
        this.errorPrefix = plugin.getConfig().getString("chat-prefix.error");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (NicknameYml.doesPlayerHaveCustomNickname(player)) {
            event.setJoinMessage(null);
            TextComponent nickname = new TextComponent(NicknameYml.getCustomNickname(player));
            nickname.setHoverEvent(
                    new HoverEvent(
                            HoverEvent.Action.SHOW_TEXT,
                            new Text(player.getName()),
                            new Text(System.lineSeparator()),
                            new Text("Type: Player"),
                            new Text(System.lineSeparator()),
                            new Text(player.getUniqueId().toString())
                    )
            );
            nickname.setClickEvent(
                    new ClickEvent(
                            ClickEvent.Action.SUGGEST_COMMAND,
                            "/tell " + player.getName()
                    )
            );
            TranslatableComponent joinMessage = new TranslatableComponent("multiplayer.player.joined", nickname);
            Bukkit.spigot().broadcast(joinMessage);
        }
        else {
            player.sendMessage(
                    String.format(
                            ChatColor.WHITE + "[" + "%s" + ChatColor.WHITE + "] /n <닉네임>을 통해 닉네임을 설정하세요.", infoPrefix
                    )
            );
            player.playNote(player.getLocation(), Instrument.BELL, Note.natural(1, Note.Tone.G));
        }
    }
}
