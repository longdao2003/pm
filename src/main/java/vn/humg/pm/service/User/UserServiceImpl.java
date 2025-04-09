package vn.humg.pm.service.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import vn.humg.pm.dto.request.UserRequest;
import vn.humg.pm.dto.response.UserResponse;
import vn.humg.pm.entity.User;
import vn.humg.pm.mapping.UserMapping;
import vn.humg.pm.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public UserResponse createUser(UserRequest userRequest) {
        if (userRepository.existsByUsername(userRequest.getUsername())){
            throw new RuntimeException("Tên người dùng đã tồn tại");
        }

        if (userRepository.existsByEmail(userRequest.getEmail())){
            throw new RuntimeException("Email người dùng đã tồn tại");
        }

        User u=UserMapping.toEntity(userRequest);
        
        u.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        try{
            userRepository.save(u);
        }
        catch(Exception e){
            throw new RuntimeException("Người dùng đã tồn tại");
        }
        
        return UserMapping.toResponse(u);
    }

    @Override
    public UserResponse getUserInfo() {
        var context=SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(() -> new RuntimeException("Người dùng không tồn tại"));
        return UserMapping.toResponse(user);
    }
    
}
