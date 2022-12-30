package com.mai_llj.plugin.util;

import static com.mai_llj.plugin.config.config.*;

public class configParser {
    public static String confParse(String text, String playerName, Integer x, Integer y, Integer z) {
        // メッセージの置換
        text = text.replace("%player%", playerName);
        text = text.replace("%x%", String.valueOf(x));
        text = text.replace("%y%", String.valueOf(y));
        text = text.replace("%z%", String.valueOf(z));
        text = text.replace("%%server_name%%", serverName);

        return text;
    }
}
