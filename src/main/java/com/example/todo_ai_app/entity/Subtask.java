package com.example.todo_ai_app.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * TODOに紐づく個別のサブタスク（ステップ）を保持するエンティティクラス
 */
@Entity
@Table(name = "subtasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subtask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // サブタスクの内容
    @Column(nullable = false)
    private String content;

    // サブタスクの完了状態
    private boolean completed;

    // 親となるTODO。TODOが削除されたらサブタスクも削除される
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id")
    @ToString.Exclude // 循環参照防止
    private Todo todo;
}
