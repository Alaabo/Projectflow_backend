package dz.corepulse.projectflow.Mappers;

import dz.corepulse.projectflow.Models.DTO.Requests.PrivilegeRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.PrivilegeResponse;
import dz.corepulse.projectflow.Models.Entities.Privilege;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface PrivilegeMapper {

    Privilege toEntity(PrivilegeRequest dto);

    PrivilegeResponse toDto(Privilege entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(PrivilegeRequest dto, @MappingTarget Privilege entity);
}
