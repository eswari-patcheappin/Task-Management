package com.taskmanagement.repository;

import com.taskmanagement.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    
    // Find tasks by status
    List<Task> findByStatus(Task.TaskStatus status);
    
    // Find tasks by priority
    List<Task> findByPriority(Task.TaskPriority priority);
    
    // Find tasks assigned to a specific person
    List<Task> findByAssignedTo(String assignedTo);
    
    // Find tasks by status and assigned person
    List<Task> findByStatusAndAssignedTo(Task.TaskStatus status, String assignedTo);
    
    // Find tasks with deadline before a specific date
    List<Task> findByDeadlineBefore(LocalDateTime deadline);
    
    // Find tasks with deadline between two dates
    List<Task> findByDeadlineBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    // Find tasks by title containing keyword (case-insensitive search)
    List<Task> findByTitleContainingIgnoreCase(String title);
    
    // Custom query to find overdue tasks
    @Query("SELECT t FROM Task t WHERE t.deadline < :currentDate AND t.status != 'COMPLETED' AND t.status != 'CANCELLED'")
    List<Task> findOverdueTasks(@Param("currentDate") LocalDateTime currentDate);
    
    // Find tasks created between two dates
    List<Task> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    // Count tasks by status
    long countByStatus(Task.TaskStatus status);
    
    // Count tasks by priority
    long countByPriority(Task.TaskPriority priority);
}
