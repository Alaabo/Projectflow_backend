package dz.corepulse.projectflow.Mappers;


import dz.corepulse.projectflow.Models.DTO.Requests.UserRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.UserResponse;
import dz.corepulse.projectflow.Models.Entities.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Basic mapping (without relations)
    @Mapping(target = "profiles", ignore = true)
    @Mapping(target = "groups", ignore = true)
    User toEntity(UserRequest dto);

    @Mapping(target = "profileIds", expression = "java(entity.getProfiles().stream().map(p -> p.getId()).toList())")
    @Mapping(target = "groupIds", expression = "java(entity.getGroups().stream().map(g -> g.getId()).toList())")
    UserResponse toDto(User entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "profiles", ignore = true)
    @Mapping(target = "groups", ignore = true)
    void updateEntity(UserRequest dto, @MappingTarget User entity);
}
