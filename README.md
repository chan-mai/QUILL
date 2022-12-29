# QUILL
QUILLは**mc1.12.2**のみで動作するspigotプラグインです。  

## config
プラグイン導入後、自動生成されるconfig.ymlには以下のような設定があります。  
```yml
connection:
  host: "mysql://127.0.0.1:3306"
  username: ""
  password: ""
  database: "quill"
  
welcome_msg:
  server_name: "A Minecraft Server"
  first_time:
    fadein: 15
    stay: 200
    fadeout: 15
    message: "ようこそ %server_name% へ!"
    sub_message: "はじめまして, %player%!"
    chat_message: "ようこそ %server_name% へ!"
  chat_message: "おかえりなさい%player%！"
 ```
サーバー参加時のメッセージの変更や、データベースへの接続情報の変更を行うには、このファイルを編集する必要があります。
### カスタマイズ
#### データベース接続
現状**MySQLのみ対応**しています。  
データベースへの接続情報は初期時点で一部のみが記述されていますが、プラグインを正常に動作させるためには、すべての項目の設定を完了させる必要があります。
 - `connection` データベース設定
   - `host`: MySQLサーバーのホスト
   - `username`: 接続ユーザー名
   - `password`: 接続パスワード
   - `database`: 接続するデータベース名
  
#### 参加時のメッセージ
 - `welcome_msg` サーバー参加時のメッセージに関する項目
   - `server_name`: サーバーの名称設定
   - `first_time`: 初参加時のメッセージ
     - `fadein`: メッセージポップアップのフェードイン時間
     - `stay`: メッセージポップアップの継続表示時間
     - `fadeout`: メッセージポップアップのフェードアウト時間
     - `message`: 表示されるメッセージ
     - `sub_message`: メッセージの下に小さく表示されるテキスト  
     - `chat_message`: チャットに表示されるメッセージ
   - `chat_message`: 2回目以降の参加時にチャット表示されるメッセージ