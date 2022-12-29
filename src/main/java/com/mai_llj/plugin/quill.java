package com.mai_llj.plugin;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import static com.mai_llj.plugin.database.checkDB;


public final class quill extends JavaPlugin {

    // データベース
    public static String host;
    public static String database;
    public static String user;
    public static String password;
    // 参加メッセージ
    public static String serverName;
    public static int[] msgData = new int[3];
    public static String[] firstTimeMsg = new String[2];
    public static String firstTimeChatmessage;
    public static String chatMessage;
    @Override
    public void onEnable() {
        // Plugin startup logic
        // config.ymlが存在しなければファイル書き出し
        saveDefaultConfig();
        // config.ymlの読み込み
        FileConfiguration config = getConfig();

        // config.ymlの値チェック
        if (config.contains("connection") || config.contains("welcome_msg")) {
            // config.ymlを変数格納
            host = config.getString("connection.host");
            database = config.getString("connection.database");
            user = config.getString("connection.user");
            password = config.getString("connection.password");

            serverName = config.getString("welcome_msg.server_name");
            // o:fadein 1:stay 2:fadeout
            msgData[0] = config.getInt("welcome_msg.first_time.fadein");
            msgData[1] = config.getInt("welcome_msg.first_time.stay");
            msgData[2] = config.getInt("welcome_msg.first_time.fadeout");
            firstTimeMsg[0] = config.getString("welcome_msg.first_time.message");
            firstTimeMsg[1] = config.getString("welcome_msg.first_time.sub_message");
            firstTimeChatmessage = config.getString("welcome_msg.first_time.chat_message");
            chatMessage = config.getString("welcome_msg.chat_message");

            checkDB();
        } else {
            getLogger().info("コンフィグの適切な設定を行ってください。");
            // プラグインを無効化
            getServer().getPluginManager().disablePlugin(this);
        }

        // リスナーの登録
        getServer().getPluginManager().registerEvents(new eventListener(), this);
        getServer().getPluginManager().registerEvents(new commandListener(this), this);

        // 読み込み時メッセージ表示
        getLogger().info("読み込みが完了しました.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        // プラグイン停止時のメッセージ
        getLogger().info("停止しました.");
    }
}
