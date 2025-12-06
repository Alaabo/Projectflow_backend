package dz.corepulse.projectflow.Services.impl;


import dz.corepulse.projectflow.Exceptions.BusinessException;
import dz.corepulse.projectflow.Mappers.ProfileMapper;
import dz.corepulse.projectflow.Models.DTO.Requests.ProfileRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.ProfileResponse;
import dz.corepulse.projectflow.Models.Entities.Profile;
import dz.corepulse.projectflow.Repositories.ProfileRepository;
import dz.corepulse.projectflow.Services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository repository;
    private final ProfileMapper mapper;

    @Override
    public ProfileResponse create(ProfileRequest request) {
        Profile entity = mapper.toEntity(request);
        repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public ProfileResponse update(UUID id, ProfileRequest request) {
        Profile entity = repository.findById(id)
                .orElseThrow(() -> new BusinessException("Profile not found"));

        mapper.updateEntity(request, entity);
        repository.save(entity);

        return mapper.toDto(entity);
    }

    @Override
    public ProfileResponse get(UUID id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new BusinessException("Profile not found"));
    }

    @Override
    public List<ProfileResponse> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id))
            throw new BusinessException("Profile not found");

        repository.deleteById(id);
    }
}
