package br.com.tiagooliveira.medicalconsult.usuario.services;

import br.com.tiagooliveira.medicalconsult.usuario.domain.Usuario;
import br.com.tiagooliveira.medicalconsult.usuario.repositories.UsuarioRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario cadastrarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarUsuario(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuario não encontrado! ID: ", id));
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public void deletarUsuario(Long id){
        Usuario usuario = buscarUsuario(id);
        usuarioRepository.delete(usuario);
    }

    public Usuario atualizarUsuario(Usuario usuario, Long id) {
        Usuario upUsuario = buscarUsuario(id);
        upUsuario.setNomeUsuario(usuario.getNomeUsuario());
        upUsuario.setEmail(usuario.getEmail());
        upUsuario.setTelefone(usuario.getTelefone());
        upUsuario.setDataNascimento(usuario.getDataNascimento());
        upUsuario.setPermissao(usuario.getPermissao());
        return usuarioRepository.save(upUsuario);
    }
}
