package com.upgradeacessoria.service;

import com.upgradeacessoria.dto.*;
import com.upgradeacessoria.model.Treino;
import com.upgradeacessoria.repository.TreinoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TreinoService {

    @Autowired
    private TreinoRepository treinoRepository;

    public Treino salvar(Treino treino) {
        return treinoRepository.save(treino);
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

    public List<ProgressoMensalDTO> listarHistoricoProgresso(String email) {
        LocalDate hoje = LocalDate.now();
        List<ProgressoMensalDTO> historico = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            LocalDate inicioMes = hoje.minusMonths(i).withDayOfMonth(1);
            LocalDate fimMes = inicioMes.plusMonths(1).minusDays(1);

            historico.add(new ProgressoMensalDTO(
                    inicioMes.format(DateTimeFormatter.ofPattern("MMMM yyyy")),
                    treinoRepository.findTotalKmPorMes(email, inicioMes, fimMes),
                    treinoRepository.findMelhorPacePorMes(email, 5.0, inicioMes, fimMes),
                    treinoRepository.findMelhorPacePorMes(email, 10.0, inicioMes, fimMes),
                    treinoRepository.findMelhorPacePorMes(email, 21.0, inicioMes, fimMes),
                    treinoRepository.findMelhorPacePorMes(email, 42.0, inicioMes, fimMes)
            ));
        }

        return historico;
    }

    public List<Treino> listarTodos() {
        return Optional.ofNullable(treinoRepository.findAll()).orElseGet(ArrayList::new);
    }


    public Treino cadastrarTreino(Treino treino) {
        return treinoRepository.save(treino);
    }

    public Treino atualizarTreino(Long id, Treino treino) {
        Treino existente = treinoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Treino não encontrado!"));

        existente.setData(treino.getData());
        existente.setDistancia(treino.getDistancia());
        existente.setDuracao(treino.getDuracao());
        existente.setTipo(treino.getTipo());
        existente.setObservacoes(treino.getObservacoes());
        existente.setUsuario(treino.getUsuario());

        return treinoRepository.save(existente);
    }

    public void excluirTreino(Long id) {
        treinoRepository.deleteById(id);
    }

    public List<Treino> listarTreinosPorAtleta(String email) {
        return treinoRepository.findByUsuarioEmailOrderByDataDesc(email);
    }

    public boolean treinoQuebraRecorde(Treino treino) {
        Double recordeAtual = treinoRepository.findMelhorPacePorDistancia(treino.getUsuario().getEmail(), treino.getDistancia());

        if (recordeAtual == null || (treino.getDuracao() / treino.getDistancia()) < recordeAtual) {
            return true;
        }

        return false;
    }

    public List<ProgressoMensalDTO> listarRelatorioGeral() {
        LocalDate hoje = LocalDate.now();
        List<ProgressoMensalDTO> relatorio = new ArrayList<>();

        for (int i = 0; i < 12; i++) { // Últimos 12 meses
            LocalDate inicioMes = hoje.minusMonths(i).withDayOfMonth(1);
            LocalDate fimMes = inicioMes.plusMonths(1).minusDays(1);

            relatorio.add(new ProgressoMensalDTO(
                    inicioMes.format(DateTimeFormatter.ofPattern("MMMM yyyy")),
                    treinoRepository.findTotalKmPorPeriodo(inicioMes, fimMes),
                    treinoRepository.findMelhorPacePorPeriodo(5.0, inicioMes, fimMes),
                    treinoRepository.findMelhorPacePorPeriodo(10.0, inicioMes, fimMes),
                    treinoRepository.findMelhorPacePorPeriodo(21.0, inicioMes, fimMes),
                    treinoRepository.findMelhorPacePorPeriodo(42.0, inicioMes, fimMes)
            ));
        }

        return relatorio;
    }

    public List<RelatorioGeralDTO> gerarRelatorioGeral() {
        LocalDate hoje = LocalDate.now();
        List<RelatorioGeralDTO> relatorio = new ArrayList<>();

        for (int i = 0; i < 12; i++) { // Últimos 12 meses
            LocalDate inicioMes = hoje.minusMonths(i).withDayOfMonth(1);
            LocalDate fimMes = inicioMes.plusMonths(1).minusDays(1);

            relatorio.add(new RelatorioGeralDTO(
                    inicioMes.format(DateTimeFormatter.ofPattern("MMMM yyyy")),
                    treinoRepository.findTotalKmPorPeriodo(inicioMes, fimMes),
                    treinoRepository.findMelhorPacePorMes("admin", 5.0, inicioMes, fimMes), // Usando método existente
                    treinoRepository.findMelhorPacePorMes("admin", 10.0, inicioMes, fimMes),
                    treinoRepository.findMelhorPacePorMes("admin", 21.0, inicioMes, fimMes),
                    treinoRepository.findMelhorPacePorMes("admin", 42.0, inicioMes, fimMes),
                    treinoRepository.findTotalTreinosPorPeriodo(inicioMes, fimMes),
                    treinoRepository.findAtletaMaisAtivo(inicioMes, fimMes)
            ));
        }

        return relatorio;
    }




    public void deletar(Long id) {
        treinoRepository.deleteById(id);
    }
}
