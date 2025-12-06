package dz.corepulse.projectflow.Mappers;

import dz.corepulse.projectflow.Models.DTO.Requests.SubTaskRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.SubTaskResponse;
import dz.corepulse.projectflow.Models.Entities.SubTask;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SubTaskMapper {

    @Mapping(target = "task", ignore = true)
    SubTask toEntity(SubTaskRequest dto);

    @Mapping(target = "taskId", expression = "java(e.getTask() != null ? e.getTask().getId() : null)")
    SubTaskResponse toDto(SubTask e);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "task", ignore = true)
    void updateEntity(SubTaskRequest dto, @MappingTarget SubTask entity);
}

