package com.example.lab23.Task;

import com.example.lab23.Task.dto.CreateTaskDto;
import com.example.lab23.Task.dto.UpdateTaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.getAllTasks();
    }

    public List<Task> getAllTasks(String sort) {
        if (Objects.equals(sort, "default")) {
            return  taskRepository.getAllTasks();
        }
        return taskRepository.getAllTasks(sort);
    }

    public Task getTaskById(Long taskId) {
        return taskRepository.getTaskById(taskId);
    }

    public Task createTask(CreateTaskDto task) {
        return taskRepository.createTask(task.getTaskName(), task.getDeadline(), task.getPriority());
    }

    public Task updateTask(Long id, UpdateTaskDto dto) {
        return taskRepository.updateTask(id, dto);
    }

    public Task deleteTask(Long id){
        return taskRepository.deleteTask(id);
    }
}
