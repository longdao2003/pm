package vn.humg.pm.service.Password;

import vn.humg.pm.dto.request.PasswordRequest;
import vn.humg.pm.dto.response.PasswordResponse;

public interface PasswordService {
    PasswordResponse createPassword(PasswordRequest request);
    PasswordResponse editPassword(Integer passwordId ,PasswordRequest request);
    PasswordResponse deletePassword(Integer passwordId);
    PasswordResponse getPasswordById(Integer passwordId);


} 