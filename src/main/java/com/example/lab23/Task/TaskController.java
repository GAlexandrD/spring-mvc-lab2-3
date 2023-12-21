package com.example.lab23.Task;

import com.example.lab23.Task.dto.CreateTaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@Controller
public class TaskController {
  private final TaskService taskService;

  @Autowired
  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @GetMapping("/task-list")
  public String getAll(Model model) {
    List<Task> tasks = taskService.getAllTasks();
    model.addAttribute("tasks", tasks);
    return "task-list";
  }

  @GetMapping("/task-list-sorted")
  public String getAll(Model model, @RequestParam(name = "sort") String sort) {
    List<Task> tasks = taskService.getAllTasks(sort);
    model.addAttribute("tasks", tasks);
    return "task-list";
  }

  @GetMapping("/task-created")
  public String createTask(Model model,
      @RequestParam(name = "taskName") String taskName,
      @RequestParam(name = "deadline") String deadline,
      @RequestParam(name = "priority") Integer priority) throws ParseException {
    taskService.createTask(new CreateTaskDto(taskName, deadline, priority));
    model.addAttribute("tasks", taskService.getAllTasks());
    return "task-list";
  }

  @GetMapping("/create-task")
  public String createTask(Model model) {
    return "create-task";
  }

  @GetMapping("/delete")
  public String deleteTask(Model model, @RequestParam(name = "taskId") Long id) {
    taskService.deleteTask(id);
    model.addAttribute("tasks", taskService.getAllTasks());
    return "task-list";
  }

  @ExceptionHandler()
  public String error(Model model, Exception err) {
    model.addAttribute("error", err.getMessage());
    return "error";
  }
}
