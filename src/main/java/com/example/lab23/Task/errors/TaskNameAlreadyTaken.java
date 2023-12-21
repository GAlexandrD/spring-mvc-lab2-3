package com.example.lab23.Task.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Such name has already been taken")
public class TaskNameAlreadyTaken extends RuntimeException {
    public TaskNameAlreadyTaken(String taskName) {
        super("Task with name " + taskName + " already exists");
    }
}