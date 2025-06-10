package com.upgradeacessoria.controller;

import com.upgradeacessoria.model.Objetivo;
import com.upgradeacessoria.service.ObjetivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/objetivos")
public class ObjetivoController {

    @Autowired
    private ObjetivoService objetivoService;

    @PostMapping
    public ResponseEntity<Objetivo> criar(@RequestBody Objetivo objetivo) {
        Objetivo salvo = objetivoService.salvar(objetivo);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping
    public List<Objetivo> listar() {
        return objetivoService.listarTodos();
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Objetivo> listarPorUsuario(@PathVariable Long usuarioId) {
        return objetivoService.listarPorUsuario(usuarioId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Objetivo> buscarPorId(@PathVariable Long id) {
        return objetivoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Objetivo> atualizar(@PathVariable Long id, @RequestBody Objetivo novo) {
        return objetivoService.atualizar(id, novo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        objetivoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

