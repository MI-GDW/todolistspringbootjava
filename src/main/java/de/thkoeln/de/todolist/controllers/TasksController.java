package de.thkoeln.de.todolist.controllers;

import java.util.List;

import de.thkoeln.de.todolist.dtos.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import de.thkoeln.de.todolist.models.Task;
import de.thkoeln.de.todolist.services.TaskServiceImpl;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TasksController {
    
    @Autowired
    private TaskServiceImpl taskService;

    @GetMapping("/")
    public String index(Model model, @RequestParam(required = false) boolean completed){

        List<Task> tasks;
        if(!completed)
            tasks = taskService.getAllOpenTask();
        else
            tasks = taskService.getAllCompletedTask();

        TaskDTO taskDTO = new TaskDTO();
        model.addAttribute("tasks",tasks);
        model.addAttribute("taskDTO",taskDTO);
        model.addAttribute("completed",completed);
        return "index";
    }

    @PostMapping("/tasks")
    public String createTask(TaskDTO taskDTO){
        Task task = new Task();
        task.setName(taskDTO.getName());
        taskService.saveTask(task);
        return "redirect:/";
    }
}
