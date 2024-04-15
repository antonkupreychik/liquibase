package com.kupreychik.mapper;

import com.kupreychik.model.command.TaskCommand;
import com.kupreychik.model.dto.TaskDTO;
import com.kupreychik.model.entity.Task;
import org.mapstruct.factory.Mappers;

import java.util.List;

@org.mapstruct.Mapper
public interface TaskMapper extends Mapper<Task, TaskDTO, TaskCommand> {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Override
    Task mapToEntity(TaskDTO taskDTO);

    @Override
    default TaskDTO mapToDTO(Task task) {
        return TaskDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .build();
    }

    Task mapToEntity(TaskCommand taskCommand);

    List<TaskDTO> listToDTO(List<Task> tasks);

    @Override
    TaskCommand mapToCommand(Task entity);
}
