package com.mai_llj.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.mai_llj.plugin.extension.database.deletePlayerTag;
import static com.mai_llj.plugin.extension.database.setPlayerTag;
import static com.mai_llj.plugin.extension.renamePlayerTag.renamePlayerTag;

public class tagCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("tag")) {
            // 引数がaddかremoveかを判定
            if (args[0].equalsIgnoreCase("set")) {
                // 称号の反映
                // プレイヤー名を取得
                String playerName = args[1];

                // プレイヤー名からUUIDを取得
                String uuid = Bukkit.getPlayer(playerName).getUniqueId().toString();
                // 第2引数から称号名を取得
                String tagName = args[2];
                // データベースに称号を追加
                setPlayerTag(uuid, tagName);

                // プレイヤー名を変更
                renamePlayerTag(uuid);

                // 成功メッセージを送信
                sender.sendMessage(ChatColor.GREEN+playerName+"に称号["+tagName+"]を設定しました。");
                return true;
            } else if (args[0].equalsIgnoreCase("remove")) {
                // 称号の削除
                // プレイヤー名を取得
                String playerName = null;
                if(args[1] == null || args[1] == ""){
                    // なければ自身
                    playerName = sender.getName();
                }else{
                    playerName = args[1];
                }
                // プレイヤー名からUUIDを取得
                String uuid = Bukkit.getPlayer(playerName).getUniqueId().toString();
                // データベースから称号を削除
                deletePlayerTag(uuid);

                // プレイヤーを取得
                Player player = Bukkit.getPlayer(uuid);
                // プレイヤー名を無記載に変更
                player.setDisplayName(player.getName());
                player.setCustomName(player.getName());
                player.setPlayerListName(player.getName());

                // 成功メッセージを送信
                sender.sendMessage(ChatColor.GREEN+playerName+"の称号を削除しました。");

                return true;
            }else {
                // 引数がaddでもremoveでもない場合
                sender.sendMessage(ChatColor.RED+"引数が不正です。");
                return false;
            }
        } else {
            return false;
        }
    }
}
