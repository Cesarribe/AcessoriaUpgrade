package com.upgradeacessoria.service;

import com.upgradeacessoria.model.Objetivo;
import com.upgradeacessoria.repository.ObjetivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObjetivoService {

    @Autowired
    private ObjetivoRepository objetivoRepository;

    public Objetivo salvar(Objetivo objetivo) {
        return objetivoRepository.save(objetivo);
    }

    public List<Objetivo> listarTodos() {
        return objetivoRepository.findAll();
    }

    public List<Objetivo> listarPorUsuario(Long usuarioId) {
        return objetivoRepository.findByUsuarioId(usuarioId);
    }

    public Optional<Objetivo> buscarPorId(Long id) {
        return objetivoRepository.findById(id);
    }

    public Optional<Objetivo> atualizar(Long id, Objetivo novo) {
        return objetivoRepository.findById(id).map(o -> {
            o.setData(novo.getData());
            o.setDistancia(novo.getDistancia());
            o.setPace(novo.getPace());
            o.setStatus(novo.getStatus());
            return objetivoRepository.save(o);
        });
    }

    public void deletar(Long id) {
        objetivoRepository.deleteById(id);
    }
}

