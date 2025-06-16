package com.upgradeacessoria.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventoDTO {
    private String nome;
    private LocalDate data;
    private String local;
    private String tipoProva;
    private String linkInscricao;
}
