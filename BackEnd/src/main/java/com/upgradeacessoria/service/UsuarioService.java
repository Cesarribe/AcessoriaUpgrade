package com.upgradeacessoria.service;

import com.upgradeacessoria.model.Usuario;
import com.upgradeacessoria.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public boolean emailExiste(String email) {
        return usuarioRepository.existsByEmail(email);
    }
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> atualizar(Long id, Usuario novo) {
        return usuarioRepository.findById(id).map(u -> {
            u.setNome(novo.getNome());
            u.setEmail(novo.getEmail());
            u.setPapel(novo.getPapel());
            return usuarioRepository.save(u);
        });
    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
