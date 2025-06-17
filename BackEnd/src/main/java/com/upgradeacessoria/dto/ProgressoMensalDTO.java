package com.upgradeacessoria.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProgressoMensalDTO {
    private String mes;
    private Double totalKm;
    private Double melhor5km;
    private Double melhor10km;
    private Double melhor21km;
    private Double melhor42km;
}
