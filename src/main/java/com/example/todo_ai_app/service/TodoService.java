package com.example.todo_ai_app.service;

import com.example.todo_ai_app.entity.Todo;
import com.example.todo_ai_app.repository.TodoRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TODOリストのビジネスロジックを担当するサービスクラス
 */
@Service
@Transactional(readOnly = true)
public class TodoService {

    private final TodoRepository todoRepository;
    private final SubtaskRepository subtaskRepository;
    private final ChatClient chatClient;

    public TodoService(TodoRepository todoRepository, SubtaskRepository subtaskRepository, ChatClient.Builder chatClientBuilder) {
        this.todoRepository = todoRepository;
        this.subtaskRepository = subtaskRepository;
        this.chatClient = chatClientBuilder.build();
    }

    /**
     * AIを使用してTODOからサブタスク（チェックリスト）を自動生成
     * 
     * @param id 対象のTODO ID
     */
    @Transactional
    public void generateSubtasks(Long id) {
        todoRepository.findById(id).ifPresent(todo -> {
            String context = "タスク名: " + todo.getTitle();
            if (todo.getDescription() != null) {
                context += "\n詳細: " + todo.getDescription();
            }

            String response = chatClient.prompt()
                    .user("以下のタスクを完了するために必要な具体的なステップを3〜5個、箇条書きで教えてください。各行は「・」で始めてください。\n" + context)
                    .call()
                    .content();

            // レスポンスをパースしてSubtaskとして保存
            response.lines()
                    .filter(line -> line.trim().startsWith("・"))
                    .map(line -> line.replaceFirst("・", "").trim())
                    .forEach(content -> {
                        Subtask subtask = Subtask.builder()
                                .content(content)
                                .completed(false)
                                .todo(todo)
                                .build();
                        subtaskRepository.save(subtask);
                    });
        });
    }

    /**
     * サブタスクの完了状態を切り替え
     * 
     * @param id サブタスク ID
     */
    @Transactional
    public void toggleSubtaskCompletion(Long id) {
        subtaskRepository.findById(id).ifPresent(subtask -> {
            subtask.setCompleted(!subtask.isCompleted());
            subtaskRepository.save(subtask);
        });
    }

    /**
     * 全てのTODOを取得します。
     * 
     * @return TODOのリスト
     */
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    /**
     * 新しいTODOを保存
     * 
     * @param todo 保存するTODOエンティティ
     * @return 保存されたTODOエンティティ
     */
    @Transactional
    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    /**
     * AIを使用してTODOの詳細を要約
     * 
     * @param id 対象のTODO ID
     */
    @Transactional
    public void summarizeTodo(Long id) {
        todoRepository.findById(id).ifPresent(todo -> {
            if (todo.getDescription() != null && !todo.getDescription().isBlank()) {
                String response = chatClient.prompt()
                        .user("以下の文章を1文で簡潔に要約してください：\n" + todo.getDescription())
                        .call()
                        .content();
                todo.setSummary(response);
                todoRepository.save(todo);
            }
        });
    }

    /**
     * TODOの完了状態を切り替え
     * 
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
     * TODOを削除
     * 
     * @param id 削除対象のTODO ID
     */
    @Transactional
    public void delete(Long id) {
        todoRepository.deleteById(id);
    }
}
