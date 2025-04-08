package vn.humg.pm.mapping;

import vn.humg.pm.dto.request.UserRequest;
import vn.humg.pm.dto.response.UserResponse;
import vn.humg.pm.entity.User;

public class UserMapping {
    public static User toEntity(UserRequest userRequest){
        User u= new User();
        u.setEmail(userRequest.getEmail());
        u.setUsername(userRequest.getUsername());
        return u;    
    }

    public static UserResponse toResponse(User u) {
        return UserResponse.builder()
            .id(u.getId())
            .email(u.getEmail())
            .username(u.getUsername())
            .build();
    }
}
