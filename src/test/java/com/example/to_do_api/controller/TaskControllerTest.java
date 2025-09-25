package com.example.to_do_api.controller;

import com.example.to_do_api.model.Task;
import com.example.to_do_api.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.security.Principal;

import static org.mockito.ArgumentMatchers.eq;

public class TaskControllerTest {

    private TaskService taskService;
    private TaskController controller;
    private Principal principal;

    @BeforeEach
    void setUp() {
        taskService = Mockito.mock(TaskService.class);
        controller = new TaskController(taskService);
        principal = () -> "user";
    }

    @Test
    void getAll_returnsTasks() {
        Task task = new Task();
        Mockito.when(taskService.getAll()).thenReturn(Flux.just(task));

        StepVerifier.create(controller.getAll())
                .expectNext(task)
                .verifyComplete();
    }

    @Test
    void create_returnsCreatedTask() {
        Task input = new Task();
        Task output = new Task();
        Mockito.when(taskService.create(eq(input), eq(principal))).thenReturn(Mono.just(output));

        StepVerifier.create(controller.create(input, principal))
                .expectNext(output)
                .verifyComplete();
    }

    @Test
    void markDone_returnsUpdatedTask() {
        Task updated = new Task();
        Mockito.when(taskService.markDone(eq(1L), eq(principal))).thenReturn(Mono.just(updated));

        StepVerifier.create(controller.markDone(1L, principal))
                .expectNext(updated)
                .verifyComplete();
    }

    @Test
    void deleteTask_completes() {
        Mockito.when(taskService.deleteTask(eq(1L), eq(principal))).thenReturn(Mono.empty());

        StepVerifier.create(controller.deleteTask(1L, principal))
                .verifyComplete();
    }

//    @Test
//    void streamTasks_emitsTasks() {
//        Task task = new Task();
//        controller.streamTasks().subscribe();
//        controller.create(task, principal).subscribe();
//        controller.sink.tryEmitNext(task);
//
//        StepVerifier.create(controller.streamTasks())
//                .then(() -> controller.sink.tryEmitNext(task))
//                .expectNext(task)
//                .thenCancel()
//                .verify();
//    }
}
