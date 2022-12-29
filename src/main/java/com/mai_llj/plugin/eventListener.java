package com.mai_llj.plugin;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Date;

public class eventListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        // 初回の参加かどうかを判定
        if (player.hasPlayedBefore()) {
            // 初回参加でない場合
            player.sendMessage(ChatColor.GREEN+"Welcome back!");
        } else {
            // 初回参加の場合
            player.sendMessage(ChatColor.GREEN+"Welcome to the server!");
            player.sendTitle("ようこそ！","§aはじめまして"+player.getName()+"さん！",20,100,20);
        }
    }

    // ブロックを設置したときのイベント
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPutBlock(PlayerInteractEvent event) {
        // プレイヤーがブロックを設置したときのみ処理
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            // プレイヤーのUUIDを取得
            String playerUUID = event.getPlayer().getUniqueId().toString();
            // ブロックの名前を取得
            Block block = event.getClickedBlock();
            String blockName = block.getType().name();
            // ブロックの座標を取得
            int x = block.getX();
            int y = block.getY();
            int z = block.getZ();
            // 現在時刻の取得
            Date date = new Date();
            // データベースへの書き込み
            database.writePutBlockLog(playerUUID, blockName, x, y, z, date);
        }
    }
}
