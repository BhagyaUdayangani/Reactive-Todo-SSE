package com.example.to_do_api.model;

import com.example.to_do_api.enums.TaskType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskEvent {
    private TaskType type;
    private Task task;
}
