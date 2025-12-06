package dz.corepulse.projectflow.Services.impl;

import dz.corepulse.projectflow.Exceptions.BusinessException;
import dz.corepulse.projectflow.Mappers.UserMapper;
import dz.corepulse.projectflow.Models.DTO.Requests.UserRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.UserResponse;
import dz.corepulse.projectflow.Models.Entities.User;
import dz.corepulse.projectflow.Repositories.GroupRepository;
import dz.corepulse.projectflow.Repositories.ProfileRepository;
import dz.corepulse.projectflow.Repositories.UserRepository;
import dz.corepulse.projectflow.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final ProfileRepository profileRepository;
    private final GroupRepository groupRepository;
    private final UserMapper mapper;

    @Override
    public UserResponse create(UserRequest request) {
        User entity = mapper.toEntity(request);

        // Attach profiles
        if (request.getProfileIds() != null)
            entity.setProfiles(profileRepository.findAllById(request.getProfileIds()));

        // Attach groups
        if (request.getGroupIds() != null)
            entity.setGroups(groupRepository.findAllById(request.getGroupIds()));

        repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public UserResponse update(UUID id, UserRequest request) {
        User entity = repository.findById(id)
                .orElseThrow(() -> new BusinessException("User not found"));

        mapper.updateEntity(request, entity);

        if (request.getProfileIds() != null)
            entity.setProfiles(profileRepository.findAllById(request.getProfileIds()));

        if (request.getGroupIds() != null)
            entity.setGroups(groupRepository.findAllById(request.getGroupIds()));

        repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public UserResponse get(UUID id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new BusinessException("User not found"));
    }

    @Override
    public List<UserResponse> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id))
            throw new BusinessException("User not found");

        repository.deleteById(id);
    }
}
