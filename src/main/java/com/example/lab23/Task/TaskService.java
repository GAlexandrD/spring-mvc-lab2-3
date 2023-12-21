package com.example.taskmanager.Task;

import com.example.taskmanager.Task.dto.CreateTaskDto;
import com.example.taskmanager.Task.dto.UpdateTaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
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

    @Transactional
    public Task createTask(CreateTaskDto task) {
        return taskRepository.createTask(task.getTaskName(), task.getDeadline(), task.getPriority());
    }

    @Transactional
    public Task updateTask(Long id, UpdateTaskDto dto) {
        return taskRepository.updateTask(id, dto);
    }

    public Task deleteTask(Long id){
        return taskRepository.deleteTask(id);
    }
}
