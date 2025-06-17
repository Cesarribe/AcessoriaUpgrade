package com.upgradeacessoria.service;

import com.upgradeacessoria.dto.EventoDTO;
import com.upgradeacessoria.model.Evento;
import com.upgradeacessoria.repository.EventoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventoService {

    private final EventoRepository eventoRepository;

    public List<EventoDTO> listarEventosFuturos() {
        LocalDate hoje = LocalDate.now();
        return eventoRepository.findByDataAfterOrderByDataAsc(hoje)
                .stream()
                .map(e -> new EventoDTO(e.getNome(), e.getData(), e.getLocal(), e.getTipoProva(), e.getLinkInscricao()))
                .toList();
    }
    public List<Evento> listarTodos() {
        return eventoRepository.findAll();
    }

    public Evento atualizarEvento(Long id, Evento evento) {
        Evento existente = eventoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Evento não encontrado!"));

        existente.setNome(evento.getNome());
        existente.setData(evento.getData());
        existente.setLocal(evento.getLocal());
        existente.setTipoProva(evento.getTipoProva());
        existente.setLinkInscricao(evento.getLinkInscricao());

        return eventoRepository.save(existente);
    }

    public Evento cadastrarEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    public void excluirEvento(Long id) {
        eventoRepository.deleteById(id);
    }

}

