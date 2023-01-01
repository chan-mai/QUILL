# QUILL
QUILLは**mc1.12.2**のみで動作するspigotプラグインです。  

## config
プラグイン導入後、`config.yml`が`./plugins/QUILL`直下のディレクトリに生成されます。
このファイルを編集することで、サーバー参加時のメッセージや、データベースへの接続情報の変更を行うことができます。
  
### ⚠注意⚠
**データベースへの接続情報は初期時点で一部のみが記述されていますが、プラグインを正常に動作させるためには、すべての項目の設定を完了させる必要があります。**  
### 各項目の名称と説明
 - **connection**: データベース設定
   - `host`: MySQLサーバーのホスト
   - `username`: 接続ユーザー名
   - `password`: 接続パスワード
   - `database`: 接続するデータベース名
<br><br>
 - `server_name`: サーバーの名称設定
 - `player_tag_format`: プレイヤーの称号表示フォーマット (フォーマット内には必ず`%tag%`と`%player%`が含まれている必要があります.)
<br><br>
 - **welcome_msg**: サーバー参加時のメッセージに関する項目
   - `first_time`: 初参加時のメッセージ
     - `fadein`: メッセージポップアップのフェードイン時間
     - `stay`: メッセージポップアップの継続表示時間
     - `fadeout`: メッセージポップアップのフェードアウト時間
     - `message`: 表示されるメッセージフォーマット
     - `sub_message`: メッセージの下に小さく表示されるテキストフォーマット  
     - `chat_message`: チャットに表示されるメッセージフォーマット
   - `chat_message`: 2回目以降の参加時にチャット表示されるメッセージ
<br><br>
 - **notificatio_death_location**: 死亡時の座標記録/表示に関する項目
    - `enable`: 死亡時の座標記録/表示のon/off切り替え (trueで有効/falseで無効)
    - `chat_message`: コマンド実行時に、チャットに表示されるメッセージフォーマット
　　
## Commands
### `/placedinfo`
プレイヤーが過去に設置したブロックの情報を表示します。  
コマンドの引数として対象のブロック座標を指定するか、コマンドを引数なしで実行後、ブロックを左クリックすると、そのブロックの情報が表示されます。
#### USAGE
`/placedinfo`  
`/placedinfo <対象ブロックのx座標> <対象ブロックのy座標> <対象ブロックのz座標>`  
Alias: `/plif`  
Alias: `/plif <対象ブロックのx座標> <対象ブロックのy座標> <対象ブロックのz座標>`
#### EXAMPLE
`/placedinfo 100 100 100`  
`/placedinfo`  
`/plif 100 100 100`  
`/plif`
___
### `/deadlocation`
プレイヤーが最後に死亡した場所の情報を表示します。  
情報には、死亡した場所の座標、死亡した時にプレイヤーがいた固有ワールド名が含まれています。  
#### USAGE
`/deadlocation`  
Alias: `/dloc`  
___
### `/tag set`
プレイヤーの称号を変更します。
#### USAGE
`/tag set <プレイヤー名> <称号>`
Alias: `/tg set <プレイヤー名> <称号>`
#### EXAMPLE
`/tag set Mai_llj 称号`  
`/tg set Mai_llj 称号`
---
### `/tag remove`
プレイヤーの称号を削除します。
#### USAGE
`/tag remove <プレイヤー名>`
Alias: `/tg remove <プレイヤー名>`
#### EXAMPLE
`/tag remove Mai_llj`  
`/tg remove Mai_llj`