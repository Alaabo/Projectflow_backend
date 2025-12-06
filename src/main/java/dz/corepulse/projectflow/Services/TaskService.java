package dz.corepulse.projectflow.Services;

import dz.corepulse.projectflow.Models.DTO.Requests.TaskRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.TaskResponse;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    TaskResponse create(TaskRequest request);

    TaskResponse update(UUID id, TaskRequest request);

    TaskResponse get(UUID id);

    List<TaskResponse> getAll();

    void delete(UUID id);
}

