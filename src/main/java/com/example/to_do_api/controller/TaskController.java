package com.example.to_do_api.controller;

import com.example.to_do_api.annotation.CurrentUser;
import com.example.to_do_api.annotation.TrackExecutionTime;
import com.example.to_do_api.model.Task;
import com.example.to_do_api.service.TaskService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.security.Principal;

/**
 * REST controller for managing tasks.
 * Provides endpoints to create, retrieve, update, delete, and stream tasks in real-time.
 * Uses reactive types (Mono, Flux) for asynchronous operations.
 * Integrates with {@link TaskService} for business logic.
 */

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final Sinks.Many<Task> sink;  // to push new events

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
        this.sink = Sinks.many().multicast().onBackpressureBuffer();
    }

    // Stream new tasks in real-time
    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Task> streamTasks() {
        return sink.asFlux();
    }

    // Get all tasks
    @TrackExecutionTime
    @GetMapping
    public Flux<Task> getAll() {
        return taskService.getAll();
    }

    // Add new task
    @TrackExecutionTime
    @PostMapping
    public Mono<Task> create(@RequestBody Task task,@CurrentUser Principal principal) {
        return taskService.create(task, principal);
    }

    // Mark as done
    @TrackExecutionTime
    @PutMapping("/{id}/done")
    public Mono<Task> markDone(@PathVariable Long id,@CurrentUser Principal principal) {
        return taskService.markDone(id, principal);
    }

    // Delete task
    @TrackExecutionTime
    @DeleteMapping("/{id}")
    public Mono<Void> deleteTask(@PathVariable Long id,@CurrentUser Principal principal) {
        return taskService.deleteTask(id, principal);
    }
}
