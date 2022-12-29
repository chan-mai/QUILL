package com.mai_llj.plugin;

import java.sql.*;
import java.util.Date;

import static com.mai_llj.plugin.config.*;

public class database {
    // データベースの接続
    public static void checkDB() {
        //データベースがあるかどうかを確認
        try {
            // データベースの接続
            Connection con = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database, user, password);
            // ステートメントの作成
            Statement stmt = con.createStatement();
            // テーブルの作成
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS putBlockLog (playerUUID VARCHAR(255), blockName VARCHAR(255), x INT, y INT, z INT, date TIMESTAMP)");
            // データベースの切断
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //putBlockLogへの書き込み
    public static void writePutBlockLog(String playerUUID, String blockName, int x, int y, int z, Timestamp date) {
        try {
            // JDBCドライバのロード
            Class.forName("com.mysql.jdbc.Driver");
            // データベースへの接続
            Connection con = DriverManager.getConnection("jdbc:mysql://"+host+"/"+database, user, password);
            // ステートメントの作成
            Statement stmt = con.createStatement();
            // SQLの実行
            stmt.executeUpdate("INSERT INTO putBlockLog (playerUUID, blockName, x, y, z, date) VALUES ('"+playerUUID+"','"+blockName+"',"+x+","+y+","+z+",'"+date+"')");
            // ステートメントのクローズ
            stmt.close();
            // データベースの切断
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // x,y,z座標からputBlockLogの最終データを取得
    public static String[] getPutBlockLog(int x, int y, int z) {
        String[] result = new String[3];
        try {
            // JDBCドライバのロード
            Class.forName("com.mysql.jdbc.Driver");
            // データベースへの接続
            Connection con = DriverManager.getConnection("jdbc:mysql://"+host+"/"+database, user, password);
            // ステートメントの作成
            Statement stmt = con.createStatement();
            // SQLの実行
            ResultSet rs = stmt.executeQuery("SELECT * FROM putBlockLog WHERE x="+x+" AND y="+y+" AND z="+z+" ORDER BY date DESC LIMIT 1");
            // 結果の取得
            while (rs.next()) {
                //データの存在確認
                if (rs.getString("playerUUID") != null) {
                    result[0] = rs.getString("playerUUID");
                    result[1] = rs.getString("blockName");
                    result[2] = rs.getString("date");
                } else {
                    result[0] = "null";
                    result[1] = "null";
                    result[2] = "null";
                }
            }
            // ステートメントのクローズ
            stmt.close();
            // データベースの切断
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
