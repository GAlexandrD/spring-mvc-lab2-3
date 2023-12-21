package com.example.lab23.Task;

import com.example.lab23.Task.dto.UpdateTaskDto;
import com.example.lab23.Task.errors.TaskNameAlreadyTaken;
import com.example.lab23.Task.errors.TaskNotFoundException;
import com.example.lab23.Task.utils.DateParser;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TaskRepositoryInMemory implements TaskRepository {
    private final List<Task> tasks;

    private final DateParser dateParser;
    private static long idCounter = 0;
    public static synchronized Long createID()
    {
        return idCounter++;
    }


    public TaskRepositoryInMemory(DateParser dateParser) {
        this.dateParser = dateParser;
        this.tasks = new ArrayList<>();
        createTask("task1", "2023-12-15", 1);
        createTask("task2", "2023-12-18", 3);
        createTask("task3", "2023-12-2", 2);
        createTask("task4", "2023-12-3", 9);
    }

    @Override
    public Task getTaskById(Long id) {
        Task task = tasks.stream().filter(task1 -> task1.getId().equals(id)).findFirst().orElse(null);
        if (task == null) throw new TaskNotFoundException(id);
        return  task;
    }
    @Override
    public List<Task> getAllTasks() {
        return tasks;
    }
    @Override
    public List<Task> getAllTasks(String sort) {
        if (sort.equals("deadline")) {
            return tasks.stream().sorted(Comparator.comparing(Task::getDeadline)).collect(Collectors.toList());
        }
        if (sort.equals("priority")) {
            return tasks.stream().sorted(Comparator.comparing(Task::getPriority)).collect(Collectors.toList());
        } else throw new Error("Incorrect sort property. You can use deadline or priority");
    }
    @Override
    public Task createTask(String taskName, String deadline, Integer priority) {
        if (isExists(taskName)) throw new TaskNameAlreadyTaken(taskName);
        Date deadlineDate = dateParser.parseDateStr(deadline);
        Task task = new Task(createID(), taskName, deadlineDate, priority, false);
        tasks.add(task);
        return task;
    }
    @Override
    public Task updateTask(Long id, UpdateTaskDto update) {
        Task task = tasks.stream().filter(task1 -> task1.getId().equals(id)).findFirst().orElse(null);
        if (task == null) throw new TaskNotFoundException(id);
        if (update.getTaskName() != null) {
            if(isExists(update.getTaskName())) throw new TaskNameAlreadyTaken(update.getTaskName());
            task.setName(update.getTaskName());
        }
        if (update.getDeadline() != null) {
            Date deadlineDate = dateParser.parseDateStr(update.getDeadline());
            task.setDeadline(deadlineDate);
        }
        if (update.getPriority() != null) task.setPriority(update.getPriority());
        if (update.getDone() != null) task.setDone(update.getDone());
        return task;
    }

    @Override
    public Task deleteTask(Long id) {
        Task task = tasks.stream().filter(task1 -> task1.getId().equals(id)).findFirst().orElse(null);
        if (task == null) throw new TaskNotFoundException(id);
        tasks.remove(task);
        return  task;
    }

    private Boolean isExists(String taskName) {
        Task existingTask = tasks.stream().filter(task1 -> task1.getName().equals(taskName)).findFirst().orElse(null);
        return existingTask != null;
    }
}
