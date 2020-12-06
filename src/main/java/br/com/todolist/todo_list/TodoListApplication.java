package br.com.todolist.todo_list;

import br.com.todolist.todo_list.views.TodoListView;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.Entity;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"br.com.todolist.todo_list.views"})
@EntityScan(basePackages = {"br.com.todolist.todo_list.models"})
@ComponentScan(basePackages = {"br.com.todolist.todo_list"})
public class TodoListApplication {
    public static void main(String[] args) {
        SpringApplication.run(TodoListApplication.class, args);
    }
}
