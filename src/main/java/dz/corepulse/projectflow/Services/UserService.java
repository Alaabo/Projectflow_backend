package dz.corepulse.projectflow.Services;


import dz.corepulse.projectflow.Models.DTO.Requests.UserRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponse create(UserRequest request);

    UserResponse update(UUID id, UserRequest request);

    UserResponse get(UUID id);

    List<UserResponse> getAll();

    void delete(UUID id);
}
