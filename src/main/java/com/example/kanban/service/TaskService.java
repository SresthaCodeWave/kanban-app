package com.example.kanban.service;

import com.example.kanban.model.Task;
import com.example.kanban.model.TaskDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {

    List<Task> getAllTasks();

    Optional<Task> getTaskById(UUID id);

    Optional<Task> getTaskByTitle(String title);

    Task saveNewTask(TaskDTO taskDTO);

    Task updateTask(Task oldTask, TaskDTO newTaskDTO);

    void deleteTask(Task task);
}
