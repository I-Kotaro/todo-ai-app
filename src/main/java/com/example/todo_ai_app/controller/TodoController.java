package com.example.todo_ai_app.controller;

import com.example.todo_ai_app.entity.Todo;
import com.example.todo_ai_app.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * TODOリストの画面表示および操作を制御するコントローラークラス
 */
@Controller
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    /**
     * トップページを表示
     * @param model 画面に渡すデータ
     * @return テンプレート名
     */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("todos", todoService.findAll());
        model.addAttribute("newTodo", new Todo());
        return "index";
    }

    /**
     * TODOを追加
     * @param todo 追加するTODO
     * @return トップページへリダイレクト
     */
    @PostMapping("/add")
    public String addTodo(@ModelAttribute Todo todo) {
        todoService.save(todo);
        return "redirect:/";
    }

    /**
     * TODOの完了状態を切り替え
     * @param id 切り替え対象のTODO ID
     * @return トップページへリダイレクト
     */
    @PostMapping("/toggle/{id}")
    public String toggleTodo(@PathVariable Long id) {
        todoService.toggleCompletion(id);
        return "redirect:/";
    }

    /**
     * AIを使用してTODOを要約
     * @param id 要約対象のTODO ID
     * @return トップページへリダイレクト
     */
    @PostMapping("/summarize/{id}")
    public String summarizeTodo(@PathVariable Long id) {
        todoService.summarizeTodo(id);
        return "redirect:/";
    }

    /**
     * AIを使用してTODOのサブタスクを生成
     * @param id 対象のTODO ID
     * @return トップページへリダイレクト
     */
    @PostMapping("/todos/{id}/generate-subtasks")
    public String generateSubtasks(@PathVariable Long id) {
        todoService.generateSubtasks(id);
        return "redirect:/";
    }

    /**
     * サブタスクの完了状態を切り替え
     * @param id サブタスク ID
     * @return トップページへリダイレクト
     */
    @PostMapping("/subtasks/{id}/toggle")
    public String toggleSubtask(@PathVariable Long id) {
        todoService.toggleSubtaskCompletion(id);
        return "redirect:/";
    }

    /**
     * TODOを削除
     * @param id 削除対象のTODO ID
     * @return トップページへリダイレクト
     */
    @PostMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Long id) {
        todoService.delete(id);
        return "redirect:/";
    }
}
