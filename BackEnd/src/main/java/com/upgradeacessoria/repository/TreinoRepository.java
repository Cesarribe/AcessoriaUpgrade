package com.upgradeacessoria.repository;



import com.upgradeacessoria.model.Treino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TreinoRepository extends JpaRepository<Treino, Long> {
    List<Treino> findByUsuarioId(Long usuarioId);
    List<Treino> findByUsuarioIdAndDataBetween(Long usuarioId, LocalDate inicio, LocalDate fim);
    Optional<Treino> findByUsuarioEmailAndData(String email, LocalDate data);
    List<Treino> findByUsuarioEmailAndDataBetween(String email, LocalDate inicio, LocalDate fim);
    @Query("SELECT MIN(t.duracao / t.distancia) FROM Treino t WHERE t.usuario.email = :email AND t.distancia = :distancia")
    Double findMelhorPacePorDistancia(@Param("email") String email, @Param("distancia") Double distancia);

    @Query("SELECT SUM(t.distancia) FROM Treino t WHERE t.usuario.email = :email AND t.data BETWEEN :inicio AND :fim")
    Double findTotalDistanciaPorPeriodo(@Param("email") String email, @Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);

}

