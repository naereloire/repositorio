package br.com.todolist.todo_list.views;

import br.com.todolist.todo_list.models.TodoListModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodoListViews extends JpaRepository<TodoListModel, Long> {
    List<TodoListModel> findByStatus(String status);

    @Query("select tag from to_do where title = :title")
    List<TodoListModel> findTagByTitle(@Param("title") String title);
}
