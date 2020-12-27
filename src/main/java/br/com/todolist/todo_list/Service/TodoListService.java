package br.com.todolist.todo_list.Service;

import br.com.todolist.todo_list.models.TodoListModel;
import br.com.todolist.todo_list.repositories.TodoListRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class TodoListService {
    private TodoListRepository todoListRepository;

    public TodoListService(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    public TodoListModel saveTodo(TodoListModel listOrder) {

        todoListRepository.save(listOrder);
        return listOrder;
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

}

//tipo/retorno/nome/parametros
