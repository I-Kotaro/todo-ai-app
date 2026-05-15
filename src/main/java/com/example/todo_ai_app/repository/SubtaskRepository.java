package com.example.todo_ai_app.repository;

import com.example.todo_ai_app.entity.Subtask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Subtaskエンティティのデータベース操作を担当するリポジトリ
 */
@Repository
public interface SubtaskRepository extends JpaRepository<Subtask, Long> {
}
