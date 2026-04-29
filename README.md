# TODO AI App

AIによる要約機能を備えたTODO管理アプリケーションです。

## 概要
本プロジェクトは、Spring Boot をベースに構築されたTODO管理システムです。  
標準的なTODO管理機能（追加・一覧表示・完了切り替え・削除）に加え、<br>
将来的にはAIを活用してTODOの詳細内容を自動的に要約する機能の搭載を予定しています。

## 特徴
- **シンプルなTODO管理**: 直感的なUIで日々のタスクを管理。
- **AI要約（予定）**: 長いタスクの説明文をAIが簡潔に要約し、一目で内容を把握可能。

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
