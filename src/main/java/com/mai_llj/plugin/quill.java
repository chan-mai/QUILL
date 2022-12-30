package com.mai_llj.plugin;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;


import static com.mai_llj.plugin.config.configLoader.*;

public final class quill extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        // config.ymlが存在しなければファイル書き出し
        saveDefaultConfig();
        // config.ymlの読み込み
        FileConfiguration config = getConfig();
        // config.ymlの値を変数に格納
        confLoad(config, this);

        // 登録
        getServer().getPluginManager().registerEvents(new eventListener(), this);
        getCommand("placedinfo").setExecutor(new commandClass());

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
