package com.upgradeacessoria.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


import com.upgradeacessoria.model.TipoTreino;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TreinoDiaDTO {
    private TipoTreino tipo;  // Agora é um ENUM, igual ao Treino
    private String objetivo;
}
