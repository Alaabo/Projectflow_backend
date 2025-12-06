package dz.corepulse.projectflow.Mappers;

import dz.corepulse.projectflow.Models.DTO.Requests.ProfileRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.ProfileResponse;
import dz.corepulse.projectflow.Models.Entities.Profile;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    Profile toEntity(ProfileRequest dto);

    ProfileResponse toDto(Profile entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(ProfileRequest dto, @MappingTarget Profile entity);
}
