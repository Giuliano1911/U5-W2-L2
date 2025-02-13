package it.blogApp.U5_W2_L2.author;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorRequest {

    @NotBlank(message = "Il campo nome non puo essere vuoto")
    private String name;

    @NotBlank(message = "Il campo cognome non puo essere vuoto")
    private String surname;

    @NotBlank(message = "Il campo email non puo essere vuoto")
    @Email(message = "Email non valida")
    private String email;

    private LocalDate birthDate;
}
