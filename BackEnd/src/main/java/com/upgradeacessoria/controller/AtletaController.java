package com.upgradeacessoria.controller;

import com.upgradeacessoria.dto.TreinoDiaDTO;
import com.upgradeacessoria.service.TreinoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/atleta")
@PreAuthorize("hasRole('ATLETA')")
@RequiredArgsConstructor
public class AtletaController {

    private final TreinoService treinoService;

    @GetMapping("/meu-treino")
    public ResponseEntity<TreinoDiaDTO> treinoDoDia(Authentication auth) {
        String email = auth.getName();
        return treinoService.buscarTreinoDoDia(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }
}
