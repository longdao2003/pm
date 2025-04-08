package vn.humg.pm.service.UserService;

import vn.humg.pm.dto.request.UserRequest;
import vn.humg.pm.dto.response.UserResponse;

public interface UserService {
    public  UserResponse  createUser(UserRequest userRequest);

    public UserResponse getUserInfo();
}
