package github.io.sweetenpotato.customnickname.commands;

import github.io.sweetenpotato.customnickname.Main;
import github.io.sweetenpotato.customnickname.yml.NicknameYml;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CustomNicknameCommand implements CommandExecutor {

    private String infoPrefix;
    private String errorPrefix;

    public CustomNicknameCommand(Main plugin) {
        this.infoPrefix = plugin.getConfig().getString("chat-prefix.info");
        this.errorPrefix = plugin.getConfig().getString("chat-prefix.error");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1) {
                if (args[0].length() >= 1) {
                    if (!args[0].equals("x")) {
                        if (args[0].length() >= 2) {
                            if (!NicknameYml.doesCustomNicknameExist(args[0])) {
                                NicknameYml.setCustomNickname(player, args[0]);
                                player.setPlayerListName(args[0]);
                                player.setDisplayName(args[0]);
                                player.setCustomName(args[0]);
                                player.setCustomNameVisible(true);
                                player.sendMessage(String.format("[%s] 서버에서 사용할 닉네임을 %s로 설정하였습니다.", this.infoPrefix, args[0]));
                            }
                            else {
                                player.sendMessage(String.format("[%s] 이 닉네임은 이미 존재합니다!", this.errorPrefix));
                            }
                        }
                        else {
                            player.sendMessage(String.format("[%s] 닉네임은 최소한 2글자 이상이어야 합니다!", this.errorPrefix));
                        }
                    }
                    else {
                        if (NicknameYml.doesPlayerHaveCustomNickname(player)) {
                            NicknameYml.delCustomNickname(player);
                            player.setPlayerListName(player.getName());
                            player.setDisplayName(player.getName());
                            player.setCustomName(player.getName());
                            player.setCustomNameVisible(false);
                            player.sendMessage(String.format("[%s] 서버에서 사용할 닉네임을 원래 닉네임으로 설정하였습니다.", this.infoPrefix));
                        }
                        else {
                            player.sendMessage(String.format("[%s] 이미 원래 닉네임을 서버에서 사용하고 있습니다. 바뀐 것이 없습니다.", this.infoPrefix));
                        }
                    }
                }
                else {
                    player.sendMessage(String.format("[%s] 서버에서 사용할 닉네임을 적어주세요! x를 입력하면 원래 닉네임을 사용합니다!", this.errorPrefix));
                }
            }
            else {
                player.sendMessage(String.format("[%s] 닉네임에는 띄어쓰기가 들어갈 수 없습니다!", this.errorPrefix));
            }
        }
        else {
            System.out.println(String.format("[%s] 이 명령어는 플레이어만 사용할 수 있습니다.", this.errorPrefix));
        }
        return true;
    }
}
