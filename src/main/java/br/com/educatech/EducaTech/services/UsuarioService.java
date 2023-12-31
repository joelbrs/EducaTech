package br.com.educatech.EducaTech.services;

import br.com.educatech.EducaTech.dtos.usuario.UsuarioDTOIn;
import br.com.educatech.EducaTech.dtos.usuario.UsuarioDTOOut;
import br.com.educatech.EducaTech.model.Aula;
import br.com.educatech.EducaTech.model.ProgressoAula;
import br.com.educatech.EducaTech.model.Usuario;
import br.com.educatech.EducaTech.repositories.AulaRepository;
import br.com.educatech.EducaTech.repositories.ProgressoAulaRepository;
import br.com.educatech.EducaTech.repositories.UsuarioRepository;
import br.com.educatech.EducaTech.utils.PadraoService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Camada de Serviço da Entidade Usuário, responsável pelas regras de negócio da aplicação em relação a essa Entidade
 * */
@Service
public class UsuarioService implements PadraoService<UsuarioDTOIn, UsuarioDTOOut> {

    /**
     * Injeção de Dependências
     * */
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

    @Override
    public List<UsuarioDTOOut> buscarTodos() {
        return usuarioRepository.findAll().stream().map(u -> modelMapper.map(u, UsuarioDTOOut.class)).toList();
    }

    /**
     * Método que busca um usuário pelo seu identificador único
     * */
    public UsuarioDTOOut buscarPorId(Long id) throws Exception {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new Exception("Usuário não encontrado, id: "+ id));
        return modelMapper.map(usuario, UsuarioDTOOut.class);
    }

    /**
     * Método que busca um usuário pelo seu e-mail
     * */
    public Usuario buscarPorEmail(String email) throws Exception {
        return usuarioRepository.findByEmail(email.split("\t")[0]).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    @Override
    @Transactional
    public UsuarioDTOOut inserir(UsuarioDTOIn dto) {
        Usuario u = new Usuario(dto.getCpf(), dto.getNome(), dto.getEmail(), dto.getSenhaNova());
        usuarioRepository.save(u);
        List<Aula> aulas = aulaRepository.findAll();

        for (Aula a : aulas) {
            progressoAulaRepository.save(new ProgressoAula(null, u, a, Boolean.FALSE));
        }

        return modelMapper.map(u, UsuarioDTOOut.class);
    }

    /**
     * Método responsável pelo Login do usuário
     * */
    public UsuarioDTOOut login(String email, String senha) throws Exception{
        Usuario usuario = buscarPorEmail(email);

        if (!usuario.getSenha().equals(senha)) {
            throw new Exception("Senha invalida!");
        }
        return modelMapper.map(usuario, UsuarioDTOOut.class);
    }

    @Override
    @Transactional
    public UsuarioDTOOut editar(Long id, UsuarioDTOIn dto) throws Exception {
        try {
            Usuario usuario = usuarioRepository.getReferenceById(id);
            usuario.setCpf(dto.getCpf());
            usuario.setNome(dto.getNome());

            if (dto.getSenhaNova() != null && !dto.getSenhaNova().isBlank()) {
                usuario = alterarSenha(dto.getSenhaAtual(), dto.getSenhaNova(), usuario);
            }

            return modelMapper.map(usuarioRepository.save(usuario), UsuarioDTOOut.class);
        }
        catch (EntityNotFoundException e) {
            throw new Exception("Usuário não encontrado, id: "+ id);
        }
    }

    /**
     * Método utilizado para alteração da senha do usuário
     * */
    public Usuario alterarSenha(String senhaAtual, String senhaNova, Usuario usuario) throws Exception {
        if (!usuario.getSenha().equals(senhaAtual)) {
            throw new Exception("Senha antiga nao corresponde a cadastrada!");
        }
        usuario.setSenha(senhaNova);
        return usuario;
    }

    @Override
    public void delete(Long id) throws Exception {
        try {
            usuarioRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new Exception("Usuário não encontrado, id: "+ id);
        }
    }
}
