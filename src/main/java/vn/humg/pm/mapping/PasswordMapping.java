package vn.humg.pm.mapping;

import vn.humg.pm.dto.request.PasswordRequest;
import vn.humg.pm.dto.response.PasswordResponse;
import vn.humg.pm.entity.Password;

public class PasswordMapping {
    public static Password toEntity(PasswordRequest request) {
        if (request == null) {
            return null;
        }

        return Password.builder()
                .isLetters(request.getIsLetters())
                .isDigits(request.getIsDigits())
                .isSymbols(request.getIsSymbols())
                .isSimilarChar(request.getIsSimilarChar())
                .passwordLength(request.getPasswordLength())
                .password(request.getPassword())
                .build();
    }


    public static PasswordResponse toResponse(Password entity) {
        if (entity == null) {
            return null;
        }
        return PasswordResponse.builder()
                .id(entity.getId())
                .isLetters(entity.getIsLetters())
                .isDigits(entity.getIsDigits())
                .isSymbols(entity.getIsSymbols())
                .isSimilarChar(entity.getIsSimilarChar())
                .passwordLength(entity.getPasswordLength())
                .password(entity.getPassword())
                .build();
    }
}
