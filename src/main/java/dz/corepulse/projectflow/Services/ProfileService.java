package dz.corepulse.projectflow.Services;


import dz.corepulse.projectflow.Models.DTO.Requests.ProfileRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.ProfileResponse;

import java.util.List;
import java.util.UUID;

public interface ProfileService {

    ProfileResponse create(ProfileRequest request);

    ProfileResponse update(UUID id, ProfileRequest request);

    ProfileResponse get(UUID id);

    List<ProfileResponse> getAll();

    void delete(UUID id);
}
