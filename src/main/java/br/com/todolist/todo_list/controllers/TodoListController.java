package br.com.todolist.todo_list.controllers;

import br.com.todolist.todo_list.Service.TodoListService;
import br.com.todolist.todo_list.models.TodoListModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

    private TodoListService todoListService;

    public TodoListController(TodoListService todoListService) {

        this.todoListService = todoListService;
    }

    @RequestMapping(value = "/todoList", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public TodoListModel create(@RequestBody TodoListModel todoList) {
        return todoListService.saveTodo(todoList);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public List<TodoListModel> getAll() {
        List<TodoListModel> listOffTodos = todoListService.findAllTodo();
        return listOffTodos;
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public ResponseEntity getById(@PathVariable("id") Long id) {
        return todoListService.findTodoById(id);
    }

    @PutMapping(value = "/id/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody TodoListModel todoListModel) {
        return todoListService.updateTodo(id,todoListModel);
    }

    @DeleteMapping(path = {"/id/{id}"})
    public ResponseEntity <?> delete (@PathVariable Long id){
        return todoListService.deleteTodo(id);
    }

}
