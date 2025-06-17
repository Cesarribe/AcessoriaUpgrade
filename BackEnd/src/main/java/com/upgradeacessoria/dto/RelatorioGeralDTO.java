package com.upgradeacessoria.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RelatorioGeralDTO {
    private String mes;
    private Double totalKm;
    private Double melhorPace5km;
    private Double melhorPace10km;
    private Double melhorPace21km;
    private Double melhorPace42km;
    private Integer totalTreinos;
    private String atletaMaisAtivo;
}

