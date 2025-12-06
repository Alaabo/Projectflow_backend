package dz.corepulse.projectflow.Mappers;



import dz.corepulse.projectflow.Models.DTO.Requests.SprintRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.SprintResponse;
import dz.corepulse.projectflow.Models.Entities.Sprint;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SprintMapper {

    @Mapping(target = "project", ignore = true)
    Sprint toEntity(SprintRequest dto);

    @Mapping(target = "projectId", expression = "java(entity.getProject() != null ? entity.getProject().getId() : null)")
    SprintResponse toDto(Sprint entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "project", ignore = true)
    void updateEntity(SprintRequest dto, @MappingTarget Sprint entity);
}

