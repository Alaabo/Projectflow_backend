package dz.corepulse.projectflow.Services;


import dz.corepulse.projectflow.Models.DTO.Requests.GroupRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.GroupResponse;

import java.util.List;
import java.util.UUID;

public interface GroupService {

    GroupResponse create(GroupRequest request);

    GroupResponse update(UUID id, GroupRequest request);

    GroupResponse get(UUID id);

    List<GroupResponse> getAll();

    void delete(UUID id);
}

