package de.thkoeln.de.todolist.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import de.thkoeln.de.todolist.models.Task;
import de.thkoeln.de.todolist.repositories.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;
      
    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    public Task getById(UUID id) throws NotFoundException{
        
        Optional<Task> taskFromRepo = taskRepository.findById(id);
        Task task;
        if(taskFromRepo.isPresent()){
            task = taskFromRepo.get();
        } else {
            throw new NotFoundException();
        }

        return task;
        
    }
      
    public Optional<Task> getTaskById(UUID id) {
        return taskRepository.findById(id);
    }
      
    public List<Task> getAllCompletedTask() {
        return taskRepository.findByCompletedTrue();
    }
      
    public List<Task> getAllOpenTask() {
        return taskRepository.findByCompletedFalse();
    }
      
    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }
      
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

}
