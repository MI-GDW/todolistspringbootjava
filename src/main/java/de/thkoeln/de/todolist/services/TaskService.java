package de.thkoeln.de.todolist.services;

import de.thkoeln.de.todolist.models.Task;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {
    public List<Task> getAllTask();

    public Task getById(UUID id) throws ChangeSetPersister.NotFoundException;

    public Optional<Task> getTaskById(UUID id);

    public List<Task> getAllCompletedTask();

    public List<Task> getAllOpenTask();

    public void deleteTask(Task task);

    public Task saveTask(Task task);
}
