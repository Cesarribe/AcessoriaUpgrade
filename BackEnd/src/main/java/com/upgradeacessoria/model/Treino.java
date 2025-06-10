package com.upgradeacessoria.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Entity
@Table(name = "treinos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Treino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data;

    private Double distancia; // em km

    private Integer duracao; // em minutos

    @Enumerated(EnumType.STRING)
    private TipoTreino tipo; // CORRIDA, INTERVALADO, LONGÃO, DESCANSO...

    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "objetivo_id")
    private Objetivo objetivo; // opcional: vínculo com o objetivo
}

