package dz.corepulse.projectflow.Services.impl;



import dz.corepulse.projectflow.Exceptions.BusinessException;
import dz.corepulse.projectflow.Mappers.GroupMapper;
import dz.corepulse.projectflow.Models.DTO.Requests.GroupRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.GroupResponse;
import dz.corepulse.projectflow.Models.Entities.Group;
import dz.corepulse.projectflow.Models.Entities.Project;
import dz.corepulse.projectflow.Repositories.GroupRepository;
import dz.corepulse.projectflow.Repositories.ProjectRepository;
import dz.corepulse.projectflow.Repositories.UserRepository;
import dz.corepulse.projectflow.Services.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository repository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final GroupMapper mapper;

    @Override
    public GroupResponse create(GroupRequest request) {
        Group entity = mapper.toEntity(request);

        // Set project
        if (request.getProjectId() != null) {
            Project project = projectRepository.findById(request.getProjectId())
                    .orElseThrow(() -> new BusinessException("Project not found"));
            entity.setProject(project);
        }

        // Set users
        if (request.getUserIds() != null)
            entity.setUsers(userRepository.findAllById(request.getUserIds()));

        repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public GroupResponse update(UUID id, GroupRequest request) {
        Group entity = repository.findById(id)
                .orElseThrow(() -> new BusinessException("Group not found"));

        mapper.updateEntity(request, entity);

        if (request.getProjectId() != null) {
            Project project = projectRepository.findById(request.getProjectId())
                    .orElseThrow(() -> new BusinessException("Project not found"));
            entity.setProject(project);
        }

        if (request.getUserIds() != null)
            entity.setUsers(userRepository.findAllById(request.getUserIds()));

        repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public GroupResponse get(UUID id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new BusinessException("Group not found"));
    }

    @Override
    public List<GroupResponse> getAll() {
        return repository.findAll()
                .stream().map(mapper::toDto).toList();
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id))
            throw new BusinessException("Group not found");

        repository.deleteById(id);
    }
}
