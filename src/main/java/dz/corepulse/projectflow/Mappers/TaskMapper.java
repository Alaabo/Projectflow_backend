package dz.corepulse.projectflow.Mappers;

import dz.corepulse.projectflow.Models.DTO.Requests.TaskRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.TaskResponse;
import dz.corepulse.projectflow.Models.Entities.Task;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(target = "story", ignore = true)
    @Mapping(target = "sprint", ignore = true)
    Task toEntity(TaskRequest dto);

    @Mapping(target = "storyId", expression = "java(e.getStory() != null ? e.getStory().getId() : null)")
    @Mapping(target = "sprintId", expression = "java(e.getSprint() != null ? e.getSprint().getId() : null)")
    TaskResponse toDto(Task e);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "story", ignore = true)
    @Mapping(target = "sprint", ignore = true)
    void updateEntity(TaskRequest dto, @MappingTarget Task entity);
}
