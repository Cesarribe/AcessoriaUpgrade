package com.upgradeacessoria.repository;

import com.upgradeacessoria.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    List<Evento> findByDataAfterOrderByDataAsc(LocalDate data);
}

