package com.example.to_do_api.service.impl;

import com.example.to_do_api.model.Task;
import com.example.to_do_api.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskServiceImplTest {

    private TaskRepository taskRepository;
    private TaskServiceImpl taskService;
    private Principal principal;

    @BeforeEach
    void setUp() {
        taskRepository = mock(TaskRepository.class);
        taskService = new TaskServiceImpl(taskRepository);
        principal = mock(Principal.class);
        when(principal.getName()).thenReturn("testUser");
    }

    @Test
    void testGetAll() {
        Task task = new Task();
        when(taskRepository.findAllByStatusTrue()).thenReturn(Flux.just(task));

        StepVerifier.create(taskService.getAll())
                .expectNext(task)
                .verifyComplete();

        verify(taskRepository).findAllByStatusTrue();
    }

    @Test
    void testCreate() {
        Task task = new Task();
        task.setTitle("Test Task");
        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        StepVerifier.create(taskService.create(task, principal))
                .assertNext(saved -> {
                    assertFalse(saved.isCompleted());
                    assertTrue(saved.isStatus());
                    assertEquals("testUser", saved.getCreatedBy());
                    assertEquals("testUser", saved.getUpdatedBy());
                })
                .verifyComplete();

        verify(taskRepository).save(any(Task.class));
    }

    @Test
    void testMarkDone() {
        Task task = new Task();
        task.setId(1L);
        when(taskRepository.findById(1L)).thenReturn(Mono.just(task));
        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        StepVerifier.create(taskService.markDone(1L, principal))
                .assertNext(updated -> {
                    assertTrue(updated.isCompleted());
                    assertEquals("testUser", updated.getUpdatedBy());
                })
                .verifyComplete();

        verify(taskRepository).findById(1L);
        verify(taskRepository).save(any(Task.class));
    }

    @Test
    void testDeleteTask() {
        Task task = new Task();
        task.setId(2L);
        when(taskRepository.findById(2L)).thenReturn(Mono.just(task));
        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        StepVerifier.create(taskService.deleteTask(2L, principal))
                .verifyComplete();

        verify(taskRepository).findById(2L);
        verify(taskRepository).save(any(Task.class));
    }

}
