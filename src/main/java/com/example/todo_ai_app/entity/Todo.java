package com.example.todo_ai_app.entity;

import jakarta.persistence.*;
import lombok.*;

//  TODO情報を保持するエンティティクラス

@Entity
@Table(name = "todos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Todo {

    // データベースで一意に識別するプライマリキー。自動採番
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODOタイトル
    @Column(nullable = false)
    private String title;

    // TODOの具体的な内容。AI要約の元になる。長いテキストを扱えるようTEXT型指定
    @Column(columnDefinition = "TEXT")
    private String description;

    // AIによって生成された要約文。要約機能が実行された際に保存
    @Column(columnDefinition = "TEXT")
    private String summary;

    // TODOの完了状態を表示
    private boolean completed;
}
