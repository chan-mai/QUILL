package com.mai_llj.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.UUID;

import static com.mai_llj.plugin.config.config.deathLocationMessage;
import static com.mai_llj.plugin.extension.database.getDeathLocation;
import static com.mai_llj.plugin.util.configParser.confParse;

public class deadLocationCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("deadlocation")) {
            // プレイヤー名を取得
            String playerName = sender.getName();
            // プレイヤー名からUUIDを取得
            String uuid = Bukkit.getPlayer(playerName).getUniqueId().toString();
            // データベースから最終死亡時の座標を取得
            String[] res = getDeathLocation(uuid);
            // メッセージテンプレートから送信メッセージを動的に生成
            String message = confParse(deathLocationMessage, Bukkit.getPlayer(UUID.fromString(uuid)).getName(), null, res[0], Integer.parseInt(res[1]), Integer.parseInt(res[2]), Integer.parseInt(res[3]));
            // プレイヤーにメッセージを送信
            sender.sendMessage(message);

            return true;
        } else {
            return false;
        }
    }

}
