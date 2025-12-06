package dz.corepulse.projectflow.Mappers;

import dz.corepulse.projectflow.Models.DTO.Requests.StoryRequest;;
import dz.corepulse.projectflow.Models.DTO.Responses.StoryResponse;
import dz.corepulse.projectflow.Models.Entities.Story;

import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface StoryMapper {

    @Mapping(target = "epic", ignore = true)
    @Mapping(target = "sprint", ignore = true)
    Story toEntity(StoryRequest dto);

    @Mapping(target = "epicId", expression = "java(entity.getEpic() != null ? entity.getEpic().getId() : null)")
    @Mapping(target = "sprintId", expression = "java(entity.getSprint() != null ? entity.getSprint().getId() : null)")
    StoryResponse toDto(Story entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "epic", ignore = true)
    @Mapping(target = "sprint", ignore = true)
    void updateEntity(StoryRequest dto, @MappingTarget Story entity);
}

