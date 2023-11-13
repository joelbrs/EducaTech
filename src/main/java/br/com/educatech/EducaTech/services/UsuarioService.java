package br.com.educatech.EducaTech.services;

import br.com.educatech.EducaTech.dtos.usuario.UsuarioDTOIn;
import br.com.educatech.EducaTech.dtos.usuario.UsuarioDTOOut;
import br.com.educatech.EducaTech.model.Usuario;
import br.com.educatech.EducaTech.repositories.UsuarioRepository;
import br.com.educatech.EducaTech.services.exceptions.RecursoNaoEncontradoException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public Page<UsuarioDTOOut> findAllPaged(String cpf, String nome, Pageable pageable) {
        Page<Usuario> usuarios = usuarioRepository.findAllPaged(cpf, nome, pageable);
        return usuarios.map(u -> modelMapper.map(u, UsuarioDTOOut.class));
    }

    public UsuarioDTOOut findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException(id));
        return modelMapper.map(usuario, UsuarioDTOOut.class);
    }

    @Transactional
    public UsuarioDTOOut insert(UsuarioDTOIn dto) {
        Usuario u = usuarioRepository.save(modelMapper.map(dto, Usuario.class));
        u.setSenha(passwordEncoder.encode(u.getSenha()));
        return modelMapper.map(u, UsuarioDTOOut.class);
    }

    @Transactional
    public UsuarioDTOOut update(Long id, UsuarioDTOIn dto) {
        try {
            Usuario usuario = usuarioRepository.getReferenceById(id);
            usuario.setCpf(dto.getCpf());
            usuario.setNome(dto.getNome());

            return modelMapper.map(usuarioRepository.save(usuario), UsuarioDTOOut.class);
        }
        catch (EntityNotFoundException e) {
            throw new RecursoNaoEncontradoException(id);
        }
    }
}
