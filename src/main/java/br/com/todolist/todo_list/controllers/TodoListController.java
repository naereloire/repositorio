package br.com.todolist.todo_list.controllers;

import br.com.todolist.todo_list.models.TodoListModel;
import br.com.todolist.todo_list.views.TodoListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @RequestMapping(value = "/{todoList}", method = RequestMethod.GET)
    @ResponseBody
    public List<TodoListModel> getAllTodoList() {
        List<TodoListModel> listOffTodos = todoListView.findAll();
        return listOffTodos;
    }
}
