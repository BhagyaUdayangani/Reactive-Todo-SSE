package com.example.to_do_api.repository;

import com.example.to_do_api.model.Task;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;

public class TaskRepositoryTest {

    @Test
    void testFindAllByStatusTrue() {
        TaskRepository repository = Mockito.mock(TaskRepository.class);
        Task task1 = new Task();
        Task task2 = new Task();
        Flux<Task> tasksFlux = Flux.fromIterable(Arrays.asList(task1, task2));

        Mockito.when(repository.findAllByStatusTrue()).thenReturn(tasksFlux);

        StepVerifier.create(repository.findAllByStatusTrue())
                .expectNext(task1)
                .expectNext(task2)
                .verifyComplete();

        Mockito.verify(repository).findAllByStatusTrue();
    }

}
