package com.mai_llj.plugin;

import java.sql.*;

import static com.mai_llj.plugin.config.config.*;

public class database {
    // データベースの接続
    public static void checkDB() {
        //データベースがあるかどうかを確認
        try {
            // データベースの接続
            Connection con = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database, user, password);
            // ステートメントの作成
            Statement stmt = con.createStatement();
            // ブロック設置ログ用テーブル
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS putBlockLog (playerUUID VARCHAR(255), blockName VARCHAR(255), x INT, y INT, z INT, date TIMESTAMP)");
            // 死亡時の座標用テーブル
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS deathLocation (playerUUID VARCHAR(255), world VARCHAR(255), x INT, y INT, z INT, date TIMESTAMP)");
            // データベースの切断
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // ブロック設置時に呼び出されputBlockLogへの書き込み
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

    // 設置座標取得時に呼び出され、x,y,z座標からputBlockLogの最終データを取得
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

    // 死亡時に呼び出され、死亡座標をdeathLocationへの書き込み
    public static void writeDeathLocation(String playerUUID, int x, int y, int z, Timestamp date) {
        try {
            // JDBCドライバのロード
            Class.forName("com.mysql.jdbc.Driver");
            // データベースへの接続
            Connection con = DriverManager.getConnection("jdbc:mysql://"+host+"/"+database, user, password);
            // ステートメントの作成
            Statement stmt = con.createStatement();
            // SQLの実行
            stmt.executeUpdate("INSERT INTO deathLocation (playerUUID, x, y, z, date) VALUES ('"+playerUUID+"',"+x+","+y+","+z+",'"+date+"')");
            // ステートメントのクローズ
            stmt.close();
            // データベースの切断
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // コマンド実行時に呼び出され、playerUUIDからdeathLocationの最終データを取得
    public static String[] getDeathLocation(String playerUUID) {
        String[] result = new String[4];
        try {
            // JDBCドライバのロード
            Class.forName("com.mysql.jdbc.Driver");
            // データベースへの接続
            Connection con = DriverManager.getConnection("jdbc:mysql://"+host+"/"+database, user, password);
            // ステートメントの作成
            Statement stmt = con.createStatement();
            // SQLの実行
            ResultSet rs = stmt.executeQuery("SELECT * FROM deathLocation WHERE playerUUID='"+playerUUID+"' ORDER BY date DESC LIMIT 1");
            // 結果の取得
            while (rs.next()) {
                //データの存在確認
                if (rs.getString("playerUUID") != null) {
                    result[0] = rs.getString("world");
                    result[1] = rs.getString("x");
                    result[2] = rs.getString("y");
                    result[3] = rs.getString("z");
                } else {
                    result[0] = "null";
                    result[1] = "null";
                    result[2] = "null";
                    result[3] = "null";
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
