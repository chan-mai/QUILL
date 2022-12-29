# QUILL
QUILLは**mc1.12.2**のみで動作するspigotプラグインです。  

## config
プラグイン導入後、`config.yml`が`./plugins/QUILL`直下のディレクトリに生成されます。
このファイルを編集することで、サーバー参加時のメッセージや、データベースへの接続情報の変更を行うことができます。
  
### ⚠注意⚠
**データベースへの接続情報は初期時点で一部のみが記述されていますが、プラグインを正常に動作させるためには、すべての項目の設定を完了させる必要があります。**  
### 各項目の名称と説明
 - `connection` **データベース設定**
   - `host`: MySQLサーバーのホスト　(※データベース形式はMySQLのみ対応しています)
   - `username`: 接続ユーザー名
   - `password`: 接続パスワード
   - `database`: 接続するデータベース名
- `server_name`: サーバーの名称設定
 - `welcome_msg` **サーバー参加時のメッセージに関する項目**
   - `first_time`: 初参加時のメッセージ
     - `fadein`: メッセージポップアップのフェードイン時間
     - `stay`: メッセージポップアップの継続表示時間
     - `fadeout`: メッセージポップアップのフェードアウト時間
     - `message`: 表示されるメッセージ
     - `sub_message`: メッセージの下に小さく表示されるテキスト  
     - `chat_message`: チャットに表示されるメッセージ
   - `chat_message`: 2回目以降の参加時にチャット表示されるメッセージ