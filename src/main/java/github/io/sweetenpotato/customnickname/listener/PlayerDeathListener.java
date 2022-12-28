package github.io.sweetenpotato.customnickname.listener;

import net.md_5.bungee.api.chat.TranslatableComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.awt.*;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        /*Player player = event.getEntity();
        player.getLastDamageCause().getCause().
        if (event.getDeathMessage())



        TranslatableComponent deathMessage = new TranslatableComponent("")
        TranslatableComponent giveMessage = new TranslatableComponent("commands.give.success");
        TranslatableComponent item = new TranslatableComponent("item.swordDiamond.name");
        item.setColor(net.md_5.bungee.api.ChatColor.GOLD);
        giveMessage.addWith(item);
        giveMessage.addWith("32");
        TextComponent username = new TextComponent("Thinkofdeath");
        username.setColor(net.md_5.bungee.api.ChatColor.AQUA);
        giveMessage.addWith(username);
        player.sendMessage(giveMessage);*/
    }
}
