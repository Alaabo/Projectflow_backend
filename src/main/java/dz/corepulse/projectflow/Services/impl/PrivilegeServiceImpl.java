package dz.corepulse.projectflow.Services.impl;

import dz.corepulse.projectflow.Mappers.PrivilegeMapper;
import dz.corepulse.projectflow.Models.DTO.Requests.PrivilegeRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.PrivilegeResponse;
import dz.corepulse.projectflow.Models.Entities.Privilege;
import dz.corepulse.projectflow.Repositories.PrivilegeRepository;
import dz.corepulse.projectflow.Services.PrivilegeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PrivilegeServiceImpl implements PrivilegeService {

    private final PrivilegeRepository repository;
    private final PrivilegeMapper mapper;

    @Override
    public PrivilegeResponse create(PrivilegeRequest request) {
        Privilege entity = mapper.toEntity(request);
        repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public PrivilegeResponse update(UUID id, PrivilegeRequest request) {
        Privilege entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Privilege not found"));
        mapper.updateEntityFromDto(request, entity);
        repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public PrivilegeResponse get(UUID id) {
        Privilege entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Privilege not found"));
        return mapper.toDto(entity);
    }

    @Override
    public List<PrivilegeResponse> getAll() {
        return repository.findAll()
                .stream().map(mapper::toDto).toList();
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id))
            throw new RuntimeException("Privilege not found");

        repository.deleteById(id);
    }
}
