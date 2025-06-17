package com.upgradeacessoria.controller;

import com.upgradeacessoria.dto.ProgressoMensalDTO;
import com.upgradeacessoria.dto.RelatorioGeralDTO;
import com.upgradeacessoria.model.Evento;
import com.upgradeacessoria.model.Treino;
import com.upgradeacessoria.service.EventoService;
import com.upgradeacessoria.service.TreinoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminController {

    private final EventoService eventoService;
    private final TreinoService treinoService;

    @PostMapping("/eventos")
    public ResponseEntity<Evento> cadastrarEvento(@RequestBody Evento evento) {
        Evento novoEvento = eventoService.cadastrarEvento(evento);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEvento);
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<Evento>> listarEventos() {
        return ResponseEntity.ok(eventoService.listarTodos());
    }

    @PutMapping("/eventos/{id}")
    public ResponseEntity<Evento> atualizarEvento(@PathVariable Long id, @RequestBody Evento evento) {
        Evento eventoAtualizado = eventoService.atualizarEvento(id, evento);
        return ResponseEntity.ok(eventoAtualizado);
    }

    @DeleteMapping("/eventos/{id}")
    public ResponseEntity<Void> excluirEvento(@PathVariable Long id) {
        eventoService.excluirEvento(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/treinos")
    public ResponseEntity<Treino> cadastrarTreino(@RequestBody Treino treino) {
        Treino novoTreino = treinoService.cadastrarTreino(treino);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoTreino);
    }


    @PutMapping("/treinos/{id}")
    public ResponseEntity<Treino> atualizarTreino(@PathVariable Long id, @RequestBody Treino treino) {
        Treino treinoAtualizado = treinoService.atualizarTreino(id, treino);
        return ResponseEntity.ok(treinoAtualizado);
    }

    @DeleteMapping("/treinos/{id}")
    public ResponseEntity<Void> excluirTreino(@PathVariable Long id) {
        treinoService.excluirTreino(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/treinos/{email}")
    public ResponseEntity<List<Treino>> listarTreinosPorAtleta(@PathVariable String email) {
        return ResponseEntity.ok(treinoService.listarTreinosPorAtleta(email));
    }
    @GetMapping("/treinos/relatorio")
    public ResponseEntity<List<ProgressoMensalDTO>> relatorioTreinosGerais() {
        return ResponseEntity.ok(treinoService.listarRelatorioGeral());
    }
    @GetMapping("/treinos")
    public ResponseEntity<List<Treino>> listar() {
        return ResponseEntity.ok(treinoService.listarTodos());
    }

    @GetMapping("/treinos/relatorio")
    public ResponseEntity<List<RelatorioGeralDTO>> relatorioTreinos() {
        return ResponseEntity.ok(treinoService.gerarRelatorioGeral());
    }
}

