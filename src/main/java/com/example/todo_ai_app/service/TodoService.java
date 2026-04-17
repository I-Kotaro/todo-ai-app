package com.example.todo_ai_app.service;

import com.example.todo_ai_app.entity.Todo;
import com.example.todo_ai_app.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TODOリストのビジネスロジックを担当するサービスクラス
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoService {

    private final TodoRepository todoRepository;

    /**
     * 全てのTODOを取得します。
     * @return TODOのリスト
     */
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    /**
     * 新しいTODOを保存します。
     * @param todo 保存するTODOエンティティ
     * @return 保存されたTODOエンティティ
     */
    @Transactional
    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    /**
     * TODOの完了状態を切り替えます。
     * @param id 対象のTODO ID
     */
    @Transactional
    public void toggleCompletion(Long id) {
        todoRepository.findById(id).ifPresent(todo -> {
            todo.setCompleted(!todo.isCompleted());
            todoRepository.save(todo);
        });
    }

    /**
     * TODOを削除します。
     * @param id 削除対象のTODO ID
     */
    @Transactional
    public void delete(Long id) {
        todoRepository.deleteById(id);
    }
}
