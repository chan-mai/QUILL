package com.mai_llj.plugin.config;

public class config {
    // 共通変数
    // ブロック設置情報取得フラグ
    public static boolean getBlockPlaceFlag = false;

    // config.ymlの値を格納する変数
    // データベース
    public static String host;
    public static String database;
    public static String user;
    public static String password;
    // 参加メッセージ
    public static String serverName;
    public static int[] firstTimeMsgData = new int[3];
    public static String[] firstTimeMsg = new String[2];
    public static String firstTimeChatmessage;
    public static String chatMessage;

    // 死亡時の座標通知
    public static boolean deathLocationFlag = false;
    public static String deathLocationMessage;


}

