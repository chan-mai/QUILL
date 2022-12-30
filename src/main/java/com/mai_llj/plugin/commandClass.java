package com.mai_llj.plugin;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

import static com.mai_llj.plugin.config.config.*;
import static com.mai_llj.plugin.database.*;
import static com.mai_llj.plugin.util.configParser.*;

public class commandClass implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // placedinfo
        if (command.getName().equalsIgnoreCase("placedinfo")) {
            if (args.length == 0) {
                sender.sendMessage("§a情報を取得したいブロックを左クリックしてください。");
                // フラグをtrueにする
                getBlockPlaceFlag = true;
                return true;
            } else { //サブコマンドの個数が0以外
                // サブコマンドにx, y, zが空でないかどうかを判定
                if (args[0].isEmpty() || args[1].isEmpty() || args[2].isEmpty()) {
                    sender.sendMessage("§c座標の形式が不正です。");
                    return false;
                }else{
                    // サブコマンドに入力された座標軸を取得
                    int x = Integer.parseInt(args[0]);
                    int y = Integer.parseInt(args[1]);
                    int z = Integer.parseInt(args[2]);

                    // データベースから設置者を取得
                    String[] res = getPutBlockLog(x, y, z);
                    // UUID
                    String uuid = res[0];
                    // ブロック名
                    String blockName = res[1];
                    // 設置日時
                    String date = res[2];
                    // UUIDからプレイヤー名を取得
                    Player player = Bukkit.getPlayer(UUID.fromString(uuid));
                    String playerName = player.getName();
                    // プレイヤーにメッセージを送信
                    sender.sendMessage(ChatColor.RED+date+" - "+blockName+" Placed "+playerName+"("+x+", "+y+", "+z+")");
                    return true;
                }
            }
        // deadlocation
        } else if (command.getName().equalsIgnoreCase("deathlocation")) {
            // プレイヤーのUUIDを取得
            String uuid = sender.getName();
            // データベースから最終死亡時の座標を取得
            String[] res = getDeathLocation(uuid);
            // メッセージテンプレートから送信メッセージを動的に生成
            String message = confParse(deathLocationMessage, sender.getName(), res[0], Integer.parseInt(res[1]), Integer.parseInt(res[2]), Integer.parseInt(res[3]));
            // プレイヤーにメッセージを送信
            sender.sendMessage(message);

            return true;
        }
        return false;
    }



}