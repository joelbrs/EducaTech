package br.com.educatech.EducaTech.services;

import br.com.educatech.EducaTech.dtos.curso.CursoDTOIn;
import br.com.educatech.EducaTech.dtos.curso.CursoDTOOut;
import br.com.educatech.EducaTech.dtos.curso.certificado.ModeloCertificadoDTO;
import br.com.educatech.EducaTech.dtos.curso.certificado.ModeloCertificadoRequestDTO;
import br.com.educatech.EducaTech.dtos.modulo.ModuloDTOOut;
import br.com.educatech.EducaTech.enums.StatusCurso;
import br.com.educatech.EducaTech.model.Certificado;
import br.com.educatech.EducaTech.model.Curso;
import br.com.educatech.EducaTech.model.ProgressoCurso;
import br.com.educatech.EducaTech.model.Usuario;
import br.com.educatech.EducaTech.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

/**
 * Camada de Serviço da Entidade Curso, responsável pelas regras de negócio da aplicação em relação a essa Entidade
 * */
@Service
public class CursoService {

    /**
     * Injeção de Dependências
     * */
    private final CursoRepository cursoRepository;
    private final ModelMapper modelMapper;
    private final ModuloService moduloService;
    private final AulaRepository aulaRepository;
    private final ProgressoCursoRepository progressoCursoRepository;
    private final CertificadoRepository certificadoRepository;
    private final UsuarioRepository usuarioRepository;

    public CursoService(CursoRepository cursoRepository, ModelMapper modelMapper, ModuloService moduloService, AulaRepository aulaRepository, ProgressoCursoRepository progressoCursoRepository, CertificadoRepository certificadoRepository, UsuarioRepository usuarioRepository) {
        this.cursoRepository = cursoRepository;
        this.modelMapper = modelMapper;
        this.moduloService = moduloService;
        this.aulaRepository = aulaRepository;
        this.progressoCursoRepository = progressoCursoRepository;
        this.certificadoRepository = certificadoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Método que busca todas os Cursos, inclusive, podendo (ou não) filtrar por Título
     * */
    @Transactional(readOnly = true)
    public List<CursoDTOOut> buscarTodos(String titulo) {
        List<Curso> cursos = cursoRepository.findAll();
        List<CursoDTOOut> dtos = cursos.stream().map(c -> modelMapper.map(c, CursoDTOOut.class)).toList();

        for (CursoDTOOut c : dtos) {
            List<ModuloDTOOut> modulos = moduloService.buscarTodosPorCurso(c.getId());
            c.setQtdModulos(modulos.size());

            for (ModuloDTOOut m : modulos) {
                c.setQtdAulas(c.getQtdAulas() + aulaRepository.findAllByCourseAndModule(c.getId(), m.getId()).size());
            }
        }

        if (titulo != null && !titulo.isBlank()) {
            return dtos.stream().filter(c -> c.getTitulo().toLowerCase().contains(titulo.toLowerCase())).toList();
        }

        return dtos;
    }

    /**
     * Método que busca um curso pelo seu identificador único
     * */
    public CursoDTOOut buscarPorId(Long id) throws Exception {
        Curso curso = cursoRepository.findById(id).orElseThrow(() -> new Exception("Curso não encontrado, id: "+ id));
        CursoDTOOut dto = modelMapper.map(curso, CursoDTOOut.class);

        List<ModuloDTOOut> modulos = moduloService.buscarTodosPorCurso(id);
        dto.setQtdModulos(modulos.size());

        for (ModuloDTOOut m : modulos) {
            dto.setQtdAulas(dto.getQtdAulas() + aulaRepository.findAllByCourseAndModule(dto.getId(), m.getId()).size());
        }
        return dto;
    }

    /**
     * Método que cria um curso a partir dos atributos da Entidade
     * */
    @Transactional
    public CursoDTOOut inserir(CursoDTOIn dto) {
        Curso curso = modelMapper.map(dto, Curso.class);

        Certificado certificado = certificadoRepository.save(new Certificado(dto.getModeloCertificado().getNome(), dto.getModeloCertificado().getArquivo(), curso));
        curso.setModeloCertificado(certificado);

        List<Usuario> usuarios = usuarioRepository.findAll();

        /**
         * Atribuindo acesso a esse Curso por todos os usuários
         * */
        for (Usuario u : usuarios) {
            progressoCursoRepository.save(new ProgressoCurso(u, curso));
        }
        return modelMapper.map(cursoRepository.save(curso), CursoDTOOut.class);
    }

    /**
     * Método para emissão do certificado de um curso
     * */
    @Transactional
    public ModeloCertificadoDTO emitirCertificado(ModeloCertificadoRequestDTO req) throws Exception {
        CursoDTOOut dto = finalizar(req.getIdCurso(), req.getIdUsuario());

        return modelMapper.map(dto.getCertificado(), ModeloCertificadoDTO.class);
    }

    /**
     * Método que busca um curso pelo seu identificador único
     * */
    @Transactional
    public CursoDTOOut finalizar(Long idCurso, Long idUsuario) throws Exception {
        ProgressoCurso progresso = progressoCursoRepository.findByIdCourseAndIdUser(idCurso, idUsuario).orElseThrow(() -> new Exception("Progresso nao encontrado! idCurso: " + idCurso + ", idUsuario: " + idUsuario));
        progresso.setStatusCurso(StatusCurso.COMPLETO.getCode());
        progresso.setDataConclusao(Instant.now());

        progressoCursoRepository.save(progresso);
        return buscarPorId(idCurso);
    }

    /**
     * Método que edita um curso
     * */
    @Transactional
    public CursoDTOOut editar(Long id, CursoDTOIn dto) throws Exception {
        try {
            Curso curso = cursoRepository.getReferenceById(id);
            curso.setTitulo(dto.getTitulo());
            curso.setCargaHoraria(dto.getCargaHoraria());
            curso.setImagem(dto.getImagem());

            return modelMapper.map(cursoRepository.save(curso), CursoDTOOut.class);
        }
        catch (EntityNotFoundException e) {
            throw new Exception("Curso não encontrado, id: "+ id);
        }
    }

    /**
     * Método que deleta um curso
     * */
    public void delete(Long id) throws Exception {
        try {
            Curso curso = cursoRepository.getReferenceById(id);

            cursoRepository.delete(curso);
        }
        catch (EntityNotFoundException e) {
            throw new Exception("Curso não encontrado, id: "+ id);
        }
    }
}
