package dz.corepulse.projectflow.Services;

import dz.corepulse.projectflow.Models.DTO.Filters.TaskFilter;
import dz.corepulse.projectflow.Models.DTO.Requests.TaskRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.PageResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.TaskResponse;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface TaskService {

    TaskResponse create(TaskRequest request);

    TaskResponse update(UUID id, TaskRequest request);

    TaskResponse get(UUID id);

    PageResponse<TaskResponse> getAll(TaskFilter filter, Pageable pageable);

    void delete(UUID id);
}
