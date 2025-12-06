package dz.corepulse.projectflow.Mappers;



import dz.corepulse.projectflow.Models.DTO.Requests.EpicRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.EpicResponse;
import dz.corepulse.projectflow.Models.Entities.Epic;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EpicMapper {

    @Mapping(target = "project", ignore = true)
    Epic toEntity(EpicRequest dto);

    @Mapping(target = "projectId", expression = "java(entity.getProject() != null ? entity.getProject().getId() : null)")
    EpicResponse toDto(Epic entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "project", ignore = true)
    void updateEntity(EpicRequest dto, @MappingTarget Epic entity);
}

