package com.foro.foro_alura.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "topics")
public class Topical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;
    @Column(unique = true)
    private String message;
    @Column(name = "date_of_creation")
    private LocalDate dateOfCreation;
    @Column(name = "topical_status")
    private boolean topicalStatus;
    private String author;
    private String course;

}
