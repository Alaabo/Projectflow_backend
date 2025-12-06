package dz.corepulse.projectflow.Services;

import dz.corepulse.projectflow.Models.DTO.Requests.SubTaskRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.SubTaskResponse;

import java.util.List;
import java.util.UUID;

public interface SubTaskService {

    SubTaskResponse create(SubTaskRequest request);

    SubTaskResponse update(UUID id, SubTaskRequest request);

    SubTaskResponse get(UUID id);

    List<SubTaskResponse> getAll();

    void delete(UUID id);
}

