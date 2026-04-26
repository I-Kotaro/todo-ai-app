# TODO AI App

AIによる要約機能を備えた、シンプルかつインテリジェントなTODO管理アプリケーションです。

## 概要
本プロジェクトは、Spring Boot をベースに構築されたTODO管理システムです。  
標準的なTODO管理機能（追加・一覧表示・完了切り替え・削除）に加え、将来的にはAI（生成AI等）を活用してTODOの詳細内容（`description`）を自動的に要約する機能の搭載を予定しています。

## 特徴
- **シンプルなTODO管理**: 直感的なUIで日々のタスクを管理。
- **AI要約（予定）**: 長いタスクの説明文をAIが簡潔に要約し、一目で内容を把握可能に。

## 技術スタック
- **Language**: Java 21
- **Framework**: Spring Boot 3.4.3
- **Build Tool**: Gradle
- **Database**: H2 Database (In-memory)
- **View**: Thymeleaf
- **ORM**: Spring Data JPA
- **Library**: Lombok

### アクセス
  - URL: 要書き足し
  - Email: 要書き足し
  - Password: 要書き足し

## プロジェクト構成
```text
src/main/java/com/example/todo_ai_app/
├── TodoAiAppApplication.java  # アプリケーション起動クラス
├── controller/                # 画面遷移とAPI制御
├── entity/                    # データモデル（Todo）
├── repository/                # データアクセス層
└── service/                   # ビジネスロジック
```

## 今後の展望
- [ ] AI要約エンジンの統合（OpenAI API等の活用）
- [ ] TODOの優先度自動判定
- [ ] 期限通知機能
- [ ] UI/UXの改善（ドラッグ＆ドロップによる並び替え等）
