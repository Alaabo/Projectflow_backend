package dz.corepulse.projectflow.Services;

import dz.corepulse.projectflow.Models.DTO.Requests.PrivilegeRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.PrivilegeResponse;

import java.util.List;
import java.util.UUID;

public interface PrivilegeService {

    PrivilegeResponse create(PrivilegeRequest request);

    PrivilegeResponse update(UUID id, PrivilegeRequest request);

    PrivilegeResponse get(UUID id);

    List<PrivilegeResponse> getAll();

    void delete(UUID id);
}
