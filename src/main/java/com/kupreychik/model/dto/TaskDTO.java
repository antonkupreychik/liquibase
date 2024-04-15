package com.kupreychik.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TaskDTO implements DTO {
    private Long id;
    private String title;
    private String description;
    private String priority;
    private String status;
    private List<TagDTO> tags;
}
