package de.thkoeln.de.todolist.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class TaskDTO {

    @Max(100)
    @Min(1)
    private String name;
    private boolean completed;

    public String getName() {
        return name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
