package com.example.to_do_api.service;

import com.example.to_do_api.model.Task;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;

@Service
public interface TaskService {

    Flux<Task> getAll();

    Mono<Task> create(Task task, Principal principal);

    Mono<Task> markDone(Long id, Principal principal);

    Mono<Void> deleteTask(Long id, Principal principal);

}
