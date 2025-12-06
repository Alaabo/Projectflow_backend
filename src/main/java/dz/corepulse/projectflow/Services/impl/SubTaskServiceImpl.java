package dz.corepulse.projectflow.Services.impl;

import dz.corepulse.projectflow.Exceptions.BusinessException;
import dz.corepulse.projectflow.Mappers.SubTaskMapper;
import dz.corepulse.projectflow.Models.DTO.Requests.SubTaskRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.SubTaskResponse;
import dz.corepulse.projectflow.Models.Entities.SubTask;
import dz.corepulse.projectflow.Repositories.SubTaskRepository;
import dz.corepulse.projectflow.Repositories.TaskRepository;
import dz.corepulse.projectflow.Services.SubTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubTaskServiceImpl implements SubTaskService {

    private final SubTaskRepository repository;
    private final TaskRepository taskRepository;
    private final SubTaskMapper mapper;

    @Override
    public SubTaskResponse create(SubTaskRequest request) {
        SubTask entity = mapper.toEntity(request);

        if (request.getTaskId() != null) {
            var task = taskRepository.findById(request.getTaskId())
                    .orElseThrow(() -> new BusinessException("Task not found"));
            entity.setTask(task);
        }

        repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public SubTaskResponse update(UUID id, SubTaskRequest request) {
        SubTask entity = repository.findById(id)
                .orElseThrow(() -> new BusinessException("Subtask not found"));

        mapper.updateEntity(request, entity);

        if (request.getTaskId() != null) {
            var task = taskRepository.findById(request.getTaskId())
                    .orElseThrow(() -> new BusinessException("Task not found"));
            entity.setTask(task);
        }

        repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public SubTaskResponse get(UUID id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new BusinessException("Subtask not found"));
    }

    @Override
    public List<SubTaskResponse> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id))
            throw new BusinessException("Subtask not found");
        repository.deleteById(id);
    }
}
