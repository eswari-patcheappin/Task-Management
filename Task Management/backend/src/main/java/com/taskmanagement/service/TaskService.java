package com.taskmanagement.service;

import com.taskmanagement.model.Task;
import com.taskmanagement.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;
    
    // Create a new task
    public Task createTask(Task task) {
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }
    
    // Get all tasks
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    
    // Get task by ID
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }
    
    // Update an existing task
    public Task updateTask(Long id, Task updatedTask) {
        return taskRepository.findById(id)
            .map(task -> {
                task.setTitle(updatedTask.getTitle());
                task.setDescription(updatedTask.getDescription());
                task.setStatus(updatedTask.getStatus());
                task.setPriority(updatedTask.getPriority());
                task.setAssignedTo(updatedTask.getAssignedTo());
                task.setDeadline(updatedTask.getDeadline());
                task.setUpdatedAt(LocalDateTime.now());
                return taskRepository.save(task);
            })
            .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }
    
    // Update task status
    public Task updateTaskStatus(Long id, Task.TaskStatus status) {
        return taskRepository.findById(id)
            .map(task -> {
                task.setStatus(status);
                task.setUpdatedAt(LocalDateTime.now());
                return taskRepository.save(task);
            })
            .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }
    
    // Assign task to a person
    public Task assignTask(Long id, String assignedTo) {
        return taskRepository.findById(id)
            .map(task -> {
                task.setAssignedTo(assignedTo);
                task.setUpdatedAt(LocalDateTime.now());
                return taskRepository.save(task);
            })
            .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }
    
    // Set task deadline
    public Task setTaskDeadline(Long id, LocalDateTime deadline) {
        return taskRepository.findById(id)
            .map(task -> {
                task.setDeadline(deadline);
                task.setUpdatedAt(LocalDateTime.now());
                return taskRepository.save(task);
            })
            .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }
    
    // Delete a task
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
    
    // Get tasks by status
    public List<Task> getTasksByStatus(Task.TaskStatus status) {
        return taskRepository.findByStatus(status);
    }
    
    // Get tasks by priority
    public List<Task> getTasksByPriority(Task.TaskPriority priority) {
        return taskRepository.findByPriority(priority);
    }
    
    // Get tasks assigned to a person
    public List<Task> getTasksByAssignedTo(String assignedTo) {
        return taskRepository.findByAssignedTo(assignedTo);
    }
    
    // Get overdue tasks
    public List<Task> getOverdueTasks() {
        return taskRepository.findOverdueTasks(LocalDateTime.now());
    }
    
    // Search tasks by title
    public List<Task> searchTasksByTitle(String title) {
        return taskRepository.findByTitleContainingIgnoreCase(title);
    }
    
    // Get tasks with upcoming deadlines (within next 7 days)
    public List<Task> getTasksWithUpcomingDeadlines() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime sevenDaysFromNow = now.plusDays(7);
        return taskRepository.findByDeadlineBetween(now, sevenDaysFromNow);
    }
}
