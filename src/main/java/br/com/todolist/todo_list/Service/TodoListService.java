package br.com.todolist.todo_list.Service;

import br.com.todolist.todo_list.models.TodoListModel;
import br.com.todolist.todo_list.repositories.TodoListRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoListService {
    private TodoListRepository todoListRepository;

    public TodoListService(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    public TodoListModel saveTodo(TodoListModel listOrder) {
        return todoListRepository.save(listOrder);
    }

    public List<TodoListModel> findAllTodo() {
        return todoListRepository.findAll();

    }

    public ResponseEntity findTodoById(Long id) {
        return todoListRepository
                .findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity updateTodo(Long id, TodoListModel todoListModel) {
        return todoListRepository.findById(id)
                .map(record -> {
                    record.setTitle(todoListModel.getTitle());
                    record.setDescription(todoListModel.getDescription());
                    record.setStatus(todoListModel.getStatus());
                    record.setTag(todoListModel.getTag());
                    TodoListModel updated = todoListRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity deleteTodo(Long id) {
        return todoListRepository.findById(id)
                .map(record -> {
                    todoListRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
