package com.example.todo_ai_app.repository;

import com.example.todo_ai_app.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
}

/*
 * JpaRepository使わないパターン
 *
 * @PersistenceContext
 * private EntityManager entityManager;
 *
 * // Todoを保存または更新
 * public Todo save(Todo todo) {
 * if (todo.getId() == null) {
 *
 * // IDがない場合は新規永続化
 * entityManager.persist(todo);
 * return todo;
 * } else {
 * // IDがある場合は既存データの更新
 * return entityManager.merge(todo);
 * }
 * }
 *
 * // IDを指定してTodoを取得
 * public Optional<Todo> findById(Long id) {
 * Todo todo = entityManager.find(Todo.class, id);
 * return Optional.ofNullable(todo);
 * }
 *
 * // 全てのTodoを取得
 * public List<Todo> findAll() {
 * return entityManager.createQuery("SELECT t FROM Todo t", Todo.class)
 * .getResultList();
 * }
 *
 * // IDを指定してTodoを削除
 * public void deleteById(Long id) {
 * findById(id).ifPresent(todo -> entityManager.remove(todo));
 * }
 */