package com.mai_llj.plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.Timestamp;
import java.util.UUID;

import static com.mai_llj.plugin.database.*;
import static com.mai_llj.plugin.config.config.*;
import static com.mai_llj.plugin.util.configParser.*;

public class eventListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        // 初回の参加かどうかを判定
        if (player.hasPlayedBefore()) {
            String msg = chatMessage.replace("%player%", player.getName()).replace("%server_name%", serverName);
            // 初回参加でない場合
            player.sendMessage(msg);

        } else {
            // 初回参加の場合
            String msg = confParse(firstTimeChatmessage, player.getName(), null, null, null, null);
            player.sendMessage(msg);
            String title = confParse(firstTimeMsg[0], player.getName(), null, null, null, null);
            String subtitle = confParse(firstTimeMsg[1], player.getName(), null, null, null, null);
            // 表示
            player.sendTitle(title,subtitle,firstTimeMsgData[0],firstTimeMsgData[1],firstTimeMsgData[2]);
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
            String blockName = block.getType().name().toString();
            // ブロックの座標を取得
            int x = block.getX();
            int y = block.getY();
            int z = block.getZ();
            // 現在時刻の取得
            Timestamp date = new Timestamp(System.currentTimeMillis());
            // データベースへの書き込み
            writePutBlockLog(playerUUID, blockName, x, y, z, date);
        }
    }

    // ブロックを破壊したときのイベント
    @EventHandler
    public void onBreakBlock(PlayerInteractEvent event) {
        // 取得用のフラグが立っている
        if (getBlockPlaceFlag) {
            // 座標の取得
            int x = event.getClickedBlock().getX();
            int y = event.getClickedBlock().getY();
            int z = event.getClickedBlock().getZ();

            // ブロック設置
            event.getClickedBlock().setType(event.getItem().getType());

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
            event.getPlayer().sendMessage(ChatColor.GREEN+date+" - "+blockName+" Placed "+playerName+"("+x+", "+y+", "+z+")");

            //フラグを初期化
            getBlockPlaceFlag = false;
        }
    }

    // 死亡時のイベント
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        // 設定が有効
        if (deathLocationFlag) {
            // プレイヤーのUUIDを取得
            String playerUUID = event.getEntity().getUniqueId().toString();
            // ワールド名を取得
            String world = event.getEntity().getWorld().getName();
            // プレイヤーの座標を取得
            int x = event.getEntity().getLocation().getBlockX();
            int y = event.getEntity().getLocation().getBlockY();
            int z = event.getEntity().getLocation().getBlockZ();
            // 現在時刻の取得
            Timestamp date = new Timestamp(System.currentTimeMillis());
            // データベースへの書き込み
            writeDeathLocation(playerUUID, world, x, y, z, date);

            // プレイヤーにメッセージを送信
            event.getEntity().sendMessage(ChatColor.RED+"最終死亡地点: ("+world+": "+x+", "+y+", "+z+")");
        }
    }
}
