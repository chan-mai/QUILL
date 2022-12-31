package com.mai_llj.plugin.util;

import static com.mai_llj.plugin.config.config.*;

public class configParser {
    public static String confParse(String text, String playerName, String tag, String world, Integer x, Integer y, Integer z) {

        // メッセージの置換
        if(playerName != null) {
            text = text.replace("%player%", playerName);
        }
        if(tag != null) {
            text = text.replace("%tag%", tag);
        }
        if(world != null) {
            text = text.replace("%world%", world);
        }
        if(x != null) {
            text = text.replace("%x%", x.toString());
        }
        if(y != null) {
            text = text.replace("%y%", y.toString());
        }
        if(z != null) {
            text = text.replace("%z%", z.toString());
        }

        text = text.replace("%server_name%", serverName);


        return text;
    }
}
