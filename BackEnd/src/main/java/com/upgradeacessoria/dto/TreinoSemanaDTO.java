package com.upgradeacessoria.dto;

import com.upgradeacessoria.model.TipoTreino;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TreinoSemanaDTO {
    private LocalDate data;
    private TipoTreino tipo;
    private Double distancia; // em km
    private Integer duracao;  // em minutos
}