package com.example.kanban.model;

import java.util.UUID;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "task")
@NoArgsConstructor
public class Task {

    @Id
    @ApiModelProperty(position = 1)
    private UUID id;

    @ApiModelProperty(position = 2)
    private String title;

    @ApiModelProperty(position = 3)
    private String description;

    @ApiModelProperty(position = 4)
    private String color;

    @ApiModelProperty(position = 5)
    private TaskStatus status;
}
