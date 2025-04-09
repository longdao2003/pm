package vn.humg.pm.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "password")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Password {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Boolean isLetters;
    private Boolean isDigits;
    private Boolean isSymbols;
    private Boolean isSimilarChar;

    private Integer passwordLength;

    @Column(nullable = false)
    private String password;
}

