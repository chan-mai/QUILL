package com.mai_llj.plugin;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.Bukkit.getServer;

public class commandListener implements Listener {
    final quill plugin;

    public commandListener(quill quill) {
        this.plugin = quill;
    }

    // ブロックの設置者, 時間, 種別を取得するコマンド
    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        // プレイヤーの取得
        Player player = event.getPlayer();
        // コマンドの取得
        String command = event.getMessage();
        // placedinfo
        if (command.equals("placedinfo")) {
            // コマンドの実行者がプレイヤーかどうかを判定
            if (player instanceof Player) {
                // リスナーの登録
                getServer().getPluginManager().registerEvents(new bbEventListener(player), this.plugin);
            }
        }
    }
}

