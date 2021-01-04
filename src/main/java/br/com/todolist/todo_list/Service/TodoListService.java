package br.com.todolist.todo_list.Service;

import br.com.todolist.todo_list.models.TodoListModel;
import br.com.todolist.todo_list.repositories.TodoListRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<TodoListModel> findTodoById(Long id) {
        return todoListRepository.findById(id);
    }

    public TodoListModel updateTodo(Long id, TodoListModel todoListModel) {

        Optional<TodoListModel> todoFounded = todoListRepository.findById(id);
        if (todoFounded.isPresent()) {
            TodoListModel record = todoFounded.get();
            record.setTitle(todoListModel.getTitle());
            record.setDescription(todoListModel.getDescription());
            record.setStatus(todoListModel.getStatus());
            record.setTag(todoListModel.getTag());
            TodoListModel updated = todoListRepository.save(record);
            return updated;
        } else {
            return null;
        }
    }

//    public ResponseEntity deleteTodo(Long id) {
//        return todoListRepository.findById(id)
//                .map(record -> {
//                    todoListRepository.deleteById(id);
//                    return ResponseEntity.ok().build();
//                }).orElse(ResponseEntity.notFound().build());
//    }

    public boolean deleteTodo(Long id) {
        Optional<TodoListModel> todoFounded = todoListRepository.findById(id);
        if (todoFounded.isPresent()) {
             todoListRepository.deleteById(id);
             return true;
        } else {
            return false;
        }
    }
}
