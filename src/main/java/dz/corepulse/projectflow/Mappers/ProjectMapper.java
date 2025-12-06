package dz.corepulse.projectflow.Mappers;

import dz.corepulse.projectflow.Models.DTO.Requests.ProjectRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.ProjectResponse;
import dz.corepulse.projectflow.Models.Entities.Project;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    @Mapping(target = "userList", ignore = true)
    Project toEntity(ProjectRequest dto);

    @Mapping(target = "userIds", expression = "java(entity.getUserList().stream().map(u -> u.getId()).toList())")
    ProjectResponse toDto(Project entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "userList", ignore = true)
    void updateEntity(ProjectRequest dto, @MappingTarget Project entity);
}
