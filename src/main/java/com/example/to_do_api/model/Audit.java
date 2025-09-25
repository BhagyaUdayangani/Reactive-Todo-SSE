package com.example.to_do_api.model;

import lombok.Data;

@Data
public class Audit {

    private boolean status;
    private long createdAt;
    private long updatedAt;
    private String createdBy;
    private String updatedBy;

}
