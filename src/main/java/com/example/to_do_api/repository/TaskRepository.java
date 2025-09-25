package com.example.to_do_api.repository;

import com.example.to_do_api.model.Task;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface TaskRepository extends ReactiveCrudRepository<Task, Long> {

    Flux<Task> findAllByStatusTrue();

}
