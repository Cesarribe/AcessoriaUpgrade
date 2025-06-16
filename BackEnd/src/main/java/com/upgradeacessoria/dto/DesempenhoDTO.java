package com.upgradeacessoria.dto;

import lombok.Builder;

@Builder
public class DesempenhoDTO {

    private Double melhor5km;
    private Double melhor10km;
    private Double melhor21km;
    private Double melhor42km;
    private Double totalSemana;
    private Double totalMes;
    private Double totalAno;

}
