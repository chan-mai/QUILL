package com.mai_llj.plugin.extension;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

import static com.mai_llj.plugin.extension.database.*;
import static com.mai_llj.plugin.config.config.*;
import static com.mai_llj.plugin.util.configParser.*;

public class renamePlayerTag {
    public static void renamePlayerTag(String playerUUID) {
        // プレイヤーのUUIDからplayerを取得
        Player player = Bukkit.getPlayer(UUID.fromString(playerUUID));
        // データベースでUUIDを参照し、称号を取得
        String tagName = getPlayerTag(playerUUID);
        // プレイヤー名
        String playerName = player.getName();

        // nullでなければ
        if (tagName != null && tagName != "") {
            // フォーマットから称号合成名を生成
            String _tagName = confParse(playerTagFormat, playerName, tagName, null, null, null, null);

            // 称号をプレイヤーに設定
            player.setDisplayName(_tagName);
            player.setCustomName(_tagName);
            player.setPlayerListName(_tagName);
        }
    }

}
