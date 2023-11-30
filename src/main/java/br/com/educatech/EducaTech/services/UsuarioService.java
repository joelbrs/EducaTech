package br.com.educatech.EducaTech.services;

import br.com.educatech.EducaTech.dtos.usuario.UsuarioDTOIn;
import br.com.educatech.EducaTech.dtos.usuario.UsuarioDTOOut;
import br.com.educatech.EducaTech.model.Aula;
import br.com.educatech.EducaTech.model.ProgressoAula;
import br.com.educatech.EducaTech.model.Usuario;
import br.com.educatech.EducaTech.repositories.AulaRepository;
import br.com.educatech.EducaTech.repositories.ProgressoAulaRepository;
import br.com.educatech.EducaTech.repositories.UsuarioRepository;
import br.com.educatech.EducaTech.services.exceptions.RecursoNaoEncontradoException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;
    private final AulaRepository aulaRepository;
    private final ProgressoAulaRepository progressoAulaRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, ModelMapper modelMapper, AulaRepository aulaRepository, ProgressoAulaRepository progressoAulaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
        this.aulaRepository = aulaRepository;
        this.progressoAulaRepository = progressoAulaRepository;
    }

    public UsuarioDTOOut findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException(id));
        return modelMapper.map(usuario, UsuarioDTOOut.class);
    }

    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email.split("\t")[0]).orElseThrow(() -> new RecursoNaoEncontradoException("Usuario nao encontrado!"));
    }

    @Transactional
    public UsuarioDTOOut insert(UsuarioDTOIn dto) {
        Usuario u = new Usuario(dto.getCpf(), dto.getNome(), dto.getEmail(), dto.getSenhaNova());
        usuarioRepository.save(u);
        List<Aula> aulas = aulaRepository.findAll();

        for (Aula a : aulas) {
            progressoAulaRepository.save(new ProgressoAula(null, u, a, Boolean.FALSE));
        }

        return modelMapper.map(u, UsuarioDTOOut.class);
    }

    public UsuarioDTOOut login(String email, String senha) throws Exception{
        Usuario usuario = findByEmail(email);

        if (!usuario.getSenha().equals(senha)) {
            throw new Exception("Senha invalida!");
        }
        return modelMapper.map(usuario, UsuarioDTOOut.class);
    }

    @Transactional
    public UsuarioDTOOut update(Long id, UsuarioDTOIn dto) throws Exception {
        try {
            Usuario usuario = usuarioRepository.getReferenceById(id);
            usuario.setCpf(dto.getCpf());
            usuario.setNome(dto.getNome());

            if (dto.getSenhaNova() != null && !dto.getSenhaNova().isBlank()) {
                usuario = updatePassword(dto.getSenhaAtual(), dto.getSenhaNova(), usuario);
            }

            return modelMapper.map(usuarioRepository.save(usuario), UsuarioDTOOut.class);
        }
        catch (EntityNotFoundException e) {
            throw new RecursoNaoEncontradoException(id);
        }
    }

    public Usuario updatePassword(String senhaAtual, String senhaNova, Usuario usuario) throws Exception {
        if (!usuario.getSenha().equals(senhaAtual)) {
            throw new Exception("Senha antiga nao corresponde a cadastrada!");
        }
        usuario.setSenha(senhaNova);
        return usuario;
    }
}
