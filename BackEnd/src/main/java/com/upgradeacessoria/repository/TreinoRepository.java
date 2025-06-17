package com.upgradeacessoria.repository;



import com.upgradeacessoria.model.Treino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TreinoRepository extends JpaRepository<Treino, Long> {
    List<Treino> findByUsuarioId(Long usuarioId);
    List<Treino> findByUsuarioIdAndDataBetween(Long usuarioId, LocalDate inicio, LocalDate fim);
    Optional<Treino> findByUsuarioEmailAndData(String email, LocalDate data);
    List<Treino> findByUsuarioEmailAndDataBetween(String email, LocalDate inicio, LocalDate fim);
    @Query("SELECT MIN(t.duracao / t.distancia) FROM Treino t WHERE t.usuario.email = :email AND t.distancia = :distancia")
    Double findMelhorPacePorDistancia(@Param("email") String email, @Param("distancia") Double distancia);

    @Query("SELECT SUM(t.distancia) FROM Treino t WHERE t.usuario.email = :email AND t.data BETWEEN :inicio AND :fim")
    Double findTotalDistanciaPorPeriodo(@Param("email") String email, @Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);

    @Query("SELECT SUM(t.distancia) FROM Treino t WHERE t.usuario.email = :email AND t.data BETWEEN :inicio AND :fim")
    Double findTotalKmPorMes(@Param("email") String email, @Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);

    @Query("SELECT MIN(t.duracao / t.distancia) FROM Treino t WHERE t.usuario.email = :email AND t.distancia = :distancia AND t.data BETWEEN :inicio AND :fim")
    Double findMelhorPacePorMes(@Param("email") String email, @Param("distancia") Double distancia, @Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);

    List<Treino> findByUsuarioEmailOrderByDataDesc(String email);
    @Query("SELECT SUM(t.distancia) FROM Treino t WHERE t.data BETWEEN :inicio AND :fim")
    Double findTotalKmPorPeriodo(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);

    @Query("SELECT MIN(t.duracao / t.distancia) FROM Treino t WHERE t.distancia = :distancia AND t.data BETWEEN :inicio AND :fim")
    Double findMelhorPacePorPeriodo(@Param("distancia") Double distancia, @Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);

    @Query("SELECT t.usuario.email FROM Treino t WHERE t.data BETWEEN :inicio AND :fim GROUP BY t.usuario.email ORDER BY COUNT(t) DESC LIMIT 1")
    String findAtletaMaisAtivo(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);

    @Query("SELECT COUNT(t) FROM Treino t WHERE t.data BETWEEN :inicio AND :fim")
    Integer findTotalTreinosPorPeriodo(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);

}

