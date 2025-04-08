package vn.humg.pm.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PasswordResponse {
    private Integer id;

    private Boolean isLetters;
    private Boolean isDigits;
    private Boolean isSymbols;
    private Boolean isSimilarChar;

    private Integer passwordLength;


    private String password;
}
