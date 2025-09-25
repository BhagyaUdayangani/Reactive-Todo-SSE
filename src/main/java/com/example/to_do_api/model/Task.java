package com.example.to_do_api.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table; // use for R2DBC

import lombok.Data;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@Table("tasks")
public class Task extends Audit {
    @Id
    private Long id;
    private String title;
    private boolean completed;
}
