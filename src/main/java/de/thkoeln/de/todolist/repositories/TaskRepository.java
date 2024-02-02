package de.thkoeln.de.todolist.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import de.thkoeln.de.todolist.models.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task,UUID> {
    public List<Task> findAll();
    public List<Task> findByCompletedTrue();
    public List<Task> findByCompletedFalse();
}

