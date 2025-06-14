package com.upgradeacessoria.service;

import com.upgradeacessoria.dto.TreinoDiaDTO;
import com.upgradeacessoria.model.Treino;
import com.upgradeacessoria.repository.TreinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TreinoService {

    @Autowired
    private TreinoRepository treinoRepository;

    public Treino salvar(Treino treino) {
        return treinoRepository.save(treino);
    }

    public List<Treino> listarTodos() {
        return treinoRepository.findAll();
    }

    public Optional<Treino> buscarPorId(Long id) {
        return treinoRepository.findById(id);
    }

    public List<Treino> listarPorUsuario(Long usuarioId) {
        return treinoRepository.findByUsuarioId(usuarioId);
    }

    public Optional<Treino> atualizar(Long id, Treino novo) {
        return treinoRepository.findById(id).map(t -> {
            t.setData(novo.getData());
            t.setDistancia(novo.getDistancia());
            t.setDuracao(novo.getDuracao());
            t.setTipo(novo.getTipo());
            t.setObservacoes(novo.getObservacoes());
            t.setObjetivo(novo.getObjetivo());
            return treinoRepository.save(t);
        });
    }
    public Optional<TreinoDiaDTO> buscarTreinoDoDia(String email) {
        LocalDate hoje = LocalDate.now();
        return treinoRepository.findByUsuarioEmailAndData(email, hoje)
                .map(t -> new TreinoDiaDTO(
                        t.getTipo(),
                        t.getObjetivo() != null ? t.getObjetivo().toString() : "Sem objetivo"
                ));
    }



    public void deletar(Long id) {
        treinoRepository.deleteById(id);
    }
}
