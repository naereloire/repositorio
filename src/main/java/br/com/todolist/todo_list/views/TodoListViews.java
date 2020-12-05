package br.com.todolist.todo_list.views;

import br.com.todolist.todo_list.models.TodoListModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoListViews extends JpaRepository<TodoListModel, Long> {
    
}
