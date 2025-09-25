package com.example.to_do_api.service.impl;

import com.example.to_do_api.enums.TaskType;
import com.example.to_do_api.model.Task;
import com.example.to_do_api.model.TaskEvent;
import com.example.to_do_api.repository.TaskRepository;
import com.example.to_do_api.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.security.Principal;

import static com.example.to_do_api.util.DateTimeUtility.getCurrentTimeInSec;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final Sinks.Many<TaskEvent> sink;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
        this.sink = Sinks.many().multicast().onBackpressureBuffer();
    }

    @Override
    public Flux<Task> getAll() {
        log.info("LOG:: Fetching all active tasks");
        return taskRepository.findAllByStatusTrue();
    }

    @Override
    public Mono<Task> create(Task task, Principal principal) {
        task.setCompleted(false);
        task.setStatus(true);
        if (task.getId() == null){
            log.info("LOG:: Creating new task: {}", task.getTitle());
            task.setCreatedBy(principal.getName());
            task.setCreatedAt(getCurrentTimeInSec());
        }
        task.setUpdatedAt(getCurrentTimeInSec());
        task.setUpdatedBy(principal.getName());
        return taskRepository.save(task)
                .doOnNext(saved -> sink.tryEmitNext(new TaskEvent(TaskType.CREATED, saved)));
    }

    @Override
    public Mono<Task> markDone(Long id, Principal principal) {
        log.info("LOG:: Marking task with ID {} as done", id);
        return taskRepository.findById(id)
                .flatMap(task -> {
                    task.setCompleted(true);
                    task.setUpdatedBy(principal.getName());
                    task.setUpdatedAt(getCurrentTimeInSec());
                    return taskRepository.save(task);
                })
                .doOnNext(updated -> sink.tryEmitNext(new TaskEvent(TaskType.UPDATED, updated)));
    }

    @Override
    public Mono<Void> deleteTask(Long id, Principal principal) {
     log.info("LOG:: Deleting task with ID {}", id);
     return taskRepository.findById(id)
             .flatMap(task -> {
                 task.setStatus(false);
                 task.setUpdatedBy(principal.getName());
                 task.setUpdatedAt(getCurrentTimeInSec());
                 return taskRepository.save(task)
                         .doOnSuccess(savedTask -> sink.tryEmitNext(new TaskEvent(TaskType.DELETED, savedTask)));
             })
             .then();
    }

}
