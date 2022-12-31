package com.mai_llj.plugin.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import static com.mai_llj.plugin.config.config.*;
import static com.mai_llj.plugin.config.config.deathLocationMessage;
import static com.mai_llj.plugin.extension.database.checkDB;
import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getServer;

public class configLoader {
    public static void confLoad(FileConfiguration config, Plugin plugin){
        // config.ymlの値チェック
        if (config.contains("connection") || config.contains("welcome_msg")) {
            // config.ymlを変数格納
            host = config.getString("connection.host");
            database = config.getString("connection.database");
            user = config.getString("connection.username");
            password = config.getString("connection.password");

            // 共通
            serverName = config.getString("server_name");
            playerTagFormat = config.getString("player_tag_format");

            // 参加時のメッセージ関連
            // o:fadein 1:stay 2:fadeout
            firstTimeMsgData[0] = config.getInt("welcome_msg.first_time.fadein");
            firstTimeMsgData[1] = config.getInt("welcome_msg.first_time.stay");
            firstTimeMsgData[2] = config.getInt("welcome_msg.first_time.fadeout");
            firstTimeMsg[0] = config.getString("welcome_msg.first_time.message");
            firstTimeMsg[1] = config.getString("welcome_msg.first_time.sub_message");
            firstTimeChatmessage = config.getString("welcome_msg.first_time.chat_message");
            chatMessage = config.getString("welcome_msg.chat_message");

            // 死亡時の座標通知
            deathLocationFlag = config.getBoolean("notificatio_death_location.enable");
            deathLocationMessage = config.getString("notificatio_death_location.command_message");

            checkDB();
        } else {
            getLogger().info("コンフィグの適切な設定を行ってください。");
            // プラグインを無効化
            getServer().getPluginManager().disablePlugin(plugin);
        }
    }
}
