package br.com.todolist.todo_list.controllers;

import br.com.todolist.todo_list.models.TodoListModel;
import br.com.todolist.todo_list.views.TodoListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api")
public class TodoListController {
    private TodoListView todoListView;

    @Autowired
    public TodoListController(TodoListView todoListView) {
        this.todoListView = todoListView;
    }

    @RequestMapping(value = "/todoList", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public TodoListModel createTodoList(@RequestBody TodoListModel todoList) {
        return todoListView.save(todoList);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public List<TodoListModel> getAllTodoList() {
        List<TodoListModel> listOffTodos = todoListView.findAll();
        return listOffTodos;
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public ResponseEntity getTodoListById(@PathVariable("id") Long id) {
        return todoListView.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "/status/{status}", method = RequestMethod.GET)
    public List<TodoListModel> getTodoListByStatus(@PathVariable("status") String status) {
        return todoListView.findByStatus(status);
    }

    @RequestMapping(value = "/tag/{tag}", method = RequestMethod.GET)
    @ResponseBody
    public List<TodoListModel> getTodoListByTag(@PathVariable("tag") String tag, Model model) {
        List<TodoListModel> listOffTodosByTag = todoListView.findTagByTitle(tag);
        return listOffTodosByTag;
    }

    @PutMapping(value = "/id/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody TodoListModel todoListModel) {
        return todoListView.findById(id)
                .map(record -> {
                    record.setTitle(todoListModel.getTitle());
                    record.setDescription(todoListModel.getDescription());
                    record.setStatus(todoListModel.getStatus());
                    record.setTag(todoListModel.getTag());
                    TodoListModel updated = todoListView.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"/id/{id}"})
    public ResponseEntity <?> delete (@PathVariable Long id){
        return todoListView.findById(id)
                .map(record->{
                    todoListView.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
