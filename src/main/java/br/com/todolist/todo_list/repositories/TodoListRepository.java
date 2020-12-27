package br.com.todolist.todo_list.repositories;

import br.com.todolist.todo_list.models.TodoListModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoListRepository extends JpaRepository<TodoListModel, Long> {}
