package de.thkoeln.de.todolist.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import de.thkoeln.de.todolist.dtos.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import de.thkoeln.de.todolist.models.Task;
import de.thkoeln.de.todolist.services.TaskServiceImpl;

@Controller
@RequestMapping("/api/v1/tasks")
public class TasksRestController {
    @Autowired
    private TaskServiceImpl taskService;
    
    @GetMapping("/")
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTask());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Task>> getTask(@PathVariable UUID id) {
        Optional<Task> task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }
    
    @GetMapping("/?completed=true")
    public ResponseEntity<List<Task>> getAllCompletedTasks() {
        return ResponseEntity.ok(taskService.getAllCompletedTask());
    }
    
    @GetMapping("/?completed=false")
    public ResponseEntity<List<Task>> getAllIncompleteTasks() {
        return ResponseEntity.ok(taskService.getAllOpenTask());
    }

    @PostMapping("/")
    public ResponseEntity<Task> createTask(@RequestBody TaskDTO taskDTO) {
        Task task = new Task();
        task.setName(taskDTO.getName());
        return ResponseEntity.ok(taskService.saveTask(task));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTask(@PathVariable UUID id, @RequestBody TaskDTO taskDTO) throws NotFoundException {
        Task task = taskService.getById(id);

        if(taskDTO.getName() != null){
            task.setName(taskDTO.getName());
        }

        task.setCompleted(taskDTO.isCompleted());

        taskService.saveTask(task);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getAllTasks(@PathVariable UUID id) throws NotFoundException {
        Task task = taskService.getById(id);
        taskService.deleteTask(task);
    }

    @DeleteMapping("/")
    public ResponseEntity<Boolean> deleteAllTasks(){
        List<Task> tasks = taskService.getAllTask();
        
        for (Task task : tasks) {
            taskService.deleteTask(task);
        }
        return ResponseEntity.ok(true);
    }
}
