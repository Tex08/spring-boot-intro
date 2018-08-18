package com.texus.springboot.web.springbootfirstwebapplication.controller;

import com.texus.springboot.web.springbootfirstwebapplication.service.TodoService;
import com.texus.springboot.web.springbootfirstwebapplication.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@SessionAttributes("name")
public class TodoController {

    @Autowired
    TodoService service;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
    }

    @RequestMapping(value="/list-todos", method = RequestMethod.GET)
    public String showTodosList(ModelMap model) {
        String name = (String) model.get("name");
        model.put("todos", service.retrieveTodos(name));
        return "list-todos";
    }

    @RequestMapping(value="/add-todo", method = RequestMethod.GET)
    public String showAddTodo(ModelMap model) {
        model.addAttribute("todo", new Todo(0, (String) model.get("name"), "Default description", new Date(), false));
        return "todo";
    }

    @RequestMapping(value="/add-todo", method = RequestMethod.POST)
    public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        if(result.hasErrors()) {
            return "todo";
        }
        service.addTodo((String) model.get("name"), todo.getDesc(), todo.getTargetDate(), false);
        return "redirect:/list-todos";
    }

    @RequestMapping(value="/delete-todo", method = RequestMethod.GET)
    public String deleteTodo(@RequestParam int id) {
        service.deleteTodo(id);
        return "redirect:/list-todos";
    }

    @RequestMapping(value="/update-todo", method = RequestMethod.GET)
    public String showUpdateTodo(@RequestParam int id, ModelMap model) {
        Todo todo = service.retrieveTodo(id);
        model.put("todo", todo);
        return "todo";
    }

    @RequestMapping(value="/update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        if(result.hasErrors()) {
            return "todo";
        }
        todo.setUser((String) model.get("name"));
        service.updateTodo(todo);
        return "redirect:/list-todos";
    }

}
