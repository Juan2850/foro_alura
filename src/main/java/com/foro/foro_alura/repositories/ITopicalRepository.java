package com.foro.foro_alura.repositories;

import com.foro.foro_alura.entities.Topical;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ITopicalRepository extends CrudRepository<Topical, Long> {

    Optional<Topical> findByTitleAndMessage(String title, String message);
}
