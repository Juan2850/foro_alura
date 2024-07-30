package com.foro.foro_alura.dto;



import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicalDTO {
    private Long id;
    @NotBlank(message = "El campo titulo es obligatorio")
    private String title;
    @NotBlank(message = "El campo mensaje es obligatorio")
    private String message;

    private LocalDate dateOfCreation;

    private boolean topicalStatus;
    @NotBlank(message = "El campo autor es obligatorio")
    private String author;
    @NotBlank(message = "El campo curso es obligatorio")
    private String course;
}
