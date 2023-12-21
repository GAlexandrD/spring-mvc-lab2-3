package com.example.lab23.Task;

import com.example.lab23.Task.dto.UpdateTaskDto;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface TaskRepository  {
    Task getTaskById(Long id);
    List<Task> getAllTasks();
    List<Task> getAllTasks(String sort);
    Task createTask(String taskName, String deadline, Integer priority);
    Task updateTask(Long id, UpdateTaskDto update);
    Task deleteTask(Long id);

}
