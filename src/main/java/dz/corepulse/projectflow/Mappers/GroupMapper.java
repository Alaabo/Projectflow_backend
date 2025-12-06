package dz.corepulse.projectflow.Mappers;

import dz.corepulse.projectflow.Models.DTO.Requests.GroupRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.GroupResponse;
import dz.corepulse.projectflow.Models.Entities.Group;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    @Mapping(target = "project", ignore = true)
    @Mapping(target = "users", ignore = true)
    Group toEntity(GroupRequest dto);

    @Mapping(target = "projectId", expression = "java(entity.getProject() != null ? entity.getProject().getId() : null)")
    @Mapping(target = "userIds", expression = "java(entity.getUsers().stream().map(u -> u.getId()).toList())")
    GroupResponse toDto(Group entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "project", ignore = true)
    @Mapping(target = "users", ignore = true)
    void updateEntity(GroupRequest dto, @MappingTarget Group entity);
}

