package com.upgradeacessoria.controller;

import com.upgradeacessoria.dto.*;
import com.upgradeacessoria.service.EventoService;
import com.upgradeacessoria.service.TreinoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/atleta")
@PreAuthorize("hasRole('ATLETA')")
@RequiredArgsConstructor
public class AtletaController {

    private final TreinoService treinoService;
    private final EventoService eventoService;

    @GetMapping("/meu-treino")
    public ResponseEntity<TreinoDiaDTO> treinoDoDia(Authentication auth) {
        String email = auth.getName();
        return treinoService.buscarTreinoDoDia(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }
    @GetMapping("/meus-treinos")
    public ResponseEntity<List<TreinoSemanaDTO>> listarTreinos(Authentication auth) {
        String email = auth.getName();
        List<TreinoSemanaDTO> treinos = treinoService.buscarTreinosDaSemana(email);
        return ResponseEntity.ok(treinos);
    }
    @GetMapping("/desempenho")
    public ResponseEntity<DesempenhoDTO> consultarDesempenho(Authentication auth) {
        String email = auth.getName();
        return ResponseEntity.ok(treinoService.calcularDesempenho(email));
    }
    @GetMapping("/eventos")
    public ResponseEntity<List<EventoDTO>> listarEventos() {
        return ResponseEntity.ok(eventoService.listarEventosFuturos());
    }

    @GetMapping("/historico")
    public ResponseEntity<List<ProgressoMensalDTO>> listarHistorico(Authentication auth) {
        String email = auth.getName();
        return ResponseEntity.ok(treinoService.listarHistoricoProgresso(email));
    }

}

