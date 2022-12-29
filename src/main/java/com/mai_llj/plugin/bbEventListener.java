package com.mai_llj.plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.UUID;

public class bbEventListener implements Listener {
    // コマンドを実行したプレイヤーを引き継ぎ
    final private Player player;

    // コンストラクタ
    public bbEventListener(Player player) {
        this.player = player;
    }

    // PlayerInteractEventが発生したときに呼び出されるメソッド
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {

        // イベントを実行したプレイヤーがコマンドを実行したプレイヤーである場合
        if (event.getPlayer() == player) {
            // プレイヤーが手でブロックを破壊しようとしたとき
            if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
                // 座標の取得
                int x = event.getClickedBlock().getX();
                int y = event.getClickedBlock().getY();
                int z = event.getClickedBlock().getZ();

                // 破壊した座標に同じ破壊したものと同じブロックを設置
                event.getPlayer().getWorld().getBlockAt(x, y, z).setType(event.getClickedBlock().getType());

                // データベースから設置者を取得
                String[] res = database.getPutBlockLog(x, y, z);
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
            }
        }
    }
}
