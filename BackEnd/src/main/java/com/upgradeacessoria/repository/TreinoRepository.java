package com.upgradeacessoria.repository;



import com.upgradeacessoria.model.Treino;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TreinoRepository extends JpaRepository<Treino, Long> {
    List<Treino> findByUsuarioId(Long usuarioId);
    List<Treino> findByUsuarioIdAndDataBetween(Long usuarioId, LocalDate inicio, LocalDate fim);
    Optional<Treino> findByUsuarioEmailAndData(String email, LocalDate data);

}

