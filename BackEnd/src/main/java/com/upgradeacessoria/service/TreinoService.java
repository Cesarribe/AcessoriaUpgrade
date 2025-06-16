package com.upgradeacessoria.service;

import com.upgradeacessoria.dto.DesempenhoDTO;
import com.upgradeacessoria.dto.TreinoDiaDTO;
import com.upgradeacessoria.dto.TreinoSemanaDTO;
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

    public List<TreinoSemanaDTO> buscarTreinosDaSemana(String email) {
        LocalDate hoje = LocalDate.now();
        LocalDate inicioSemana = hoje.minusDays(hoje.getDayOfWeek().getValue() - 1);
        LocalDate fimSemana = inicioSemana.plusDays(6);

        return treinoRepository.findByUsuarioEmailAndDataBetween(email, inicioSemana, fimSemana)
                .stream()
                .map(t -> new TreinoSemanaDTO(t.getData(), t.getTipo(), t.getDistancia(), t.getDuracao()))
                .toList();
    }

    public DesempenhoDTO calcularDesempenho(String email) {
        LocalDate hoje = LocalDate.now();
        LocalDate inicioSemana = hoje.minusDays(hoje.getDayOfWeek().getValue() - 1);
        LocalDate inicioMes = hoje.withDayOfMonth(1);
        LocalDate inicioAno = hoje.withDayOfYear(1);

        return DesempenhoDTO.builder()
                .melhor5km(treinoRepository.findMelhorPacePorDistancia(email, 5.0))
                .melhor10km(treinoRepository.findMelhorPacePorDistancia(email, 10.0))
                .melhor21km(treinoRepository.findMelhorPacePorDistancia(email, 21.0))
                .melhor42km(treinoRepository.findMelhorPacePorDistancia(email, 42.0))
                .totalSemana(treinoRepository.findTotalDistanciaPorPeriodo(email, inicioSemana, hoje))
                .totalMes(treinoRepository.findTotalDistanciaPorPeriodo(email, inicioMes, hoje))
                .totalAno(treinoRepository.findTotalDistanciaPorPeriodo(email, inicioAno, hoje))
                .build();
    }


    public void deletar(Long id) {
        treinoRepository.deleteById(id);
    }
}
