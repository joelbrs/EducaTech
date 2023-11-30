package br.com.educatech.EducaTech.services;

import br.com.educatech.EducaTech.dtos.aula.AulaDTOIn;
import br.com.educatech.EducaTech.dtos.aula.AulaDTOOut;
import br.com.educatech.EducaTech.model.*;
import br.com.educatech.EducaTech.repositories.*;
import br.com.educatech.EducaTech.services.exceptions.RecursoNaoEncontradoException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AulaService {

    private final AulaRepository aulaRepository;
    private final ModelMapper modelMapper;
    private final CursoRepository cursoRepository;
    private final ModuloRepository moduloRepository;
    private final ProgressoAulaRepository progressoAulaRepository;
    private final UsuarioRepository usuarioRepository;

    public AulaService(AulaRepository aulaRepository, ModelMapper modelMapper, CursoRepository cursoRepository, ModuloRepository moduloRepository, ProgressoAulaRepository progressoAulaRepository, UsuarioRepository usuarioRepository) {
        this.aulaRepository = aulaRepository;
        this.modelMapper = modelMapper;
        this.cursoRepository = cursoRepository;
        this.moduloRepository = moduloRepository;
        this.progressoAulaRepository = progressoAulaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public List<AulaDTOOut> findAll(String titulo, Long idCurso, Long idModulo) {
        List<Aula> aulas = aulaRepository.findAll();

        if (titulo != null && !titulo.isBlank()) {
            aulas = aulas.stream().filter(a -> a.getTitulo().toLowerCase().contains(titulo)).toList();
        }

        if (idCurso != null) {
            aulas = aulas.stream().filter(a -> a.getCurso().getId().equals(idCurso)).toList();
        }

        if (idModulo != null) {
            aulas = aulas.stream().filter(a -> a.getModulo().getId().equals(idModulo)).toList();
        }

        return aulas.stream().map(a -> modelMapper.map(a, AulaDTOOut.class)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<AulaDTOOut> findAllByCourseAndModule(Long idCurso, Long idModulo) {
        List<Aula> aulas = aulaRepository.findAllByCourseAndModule(idCurso, idModulo);
        return aulas.stream().map(a -> modelMapper.map(a, AulaDTOOut.class)).collect(Collectors.toList());
    }

    public AulaDTOOut findByCourseModuleAndOrder(Long idCurso, Long idModulo, Integer ordem) {
        List<Aula> aulas = aulaRepository.findAllByCourseAndModule(idCurso, idModulo);

        if (!aulas.isEmpty()) {
            for (Aula a : aulas) {
                if (a.getOrdem().equals(ordem)) {
                    Aula aula = new Aula(a.getCurso(), a.getModulo(), a.getTitulo(), a.getDescricao(), a.getOrdem(), a.getVideo());
                    return modelMapper.map(aula, AulaDTOOut.class);
                }
            }
        }
        throw new RecursoNaoEncontradoException("Aula não encontrada!");
    }

    @Transactional(readOnly = true)
    public Integer findNextOrder(Long idCurso, Long idModulo) {
        Aula aula = aulaRepository.findAulaWithMaxOrder(idCurso, idModulo).orElse(null);

        if (aula == null) {
            return 1;
        }
        return aula.getOrdem() + 1;
    }

    @Transactional(readOnly = true)
    public Boolean verificarAulaAssistida(Long idAula, Long idUsuario) {
        ProgressoAula progressoAula = progressoAulaRepository.findByIdAulaAndIdUsuario(idAula, idUsuario).orElseThrow(() -> new RecursoNaoEncontradoException(idAula));
        return progressoAula.getAssistida();
    }

    @Transactional
    public AulaDTOOut insert(AulaDTOIn dto) {
        Curso curso = modelMapper.map(cursoRepository.findById(dto.getCurso()).orElseThrow(() -> new RecursoNaoEncontradoException("Curso nao encontrado, ID: " + dto.getCurso())), Curso.class);
        Modulo modulo = modelMapper.map(moduloRepository.findById(dto.getModulo()).orElseThrow(()->new RecursoNaoEncontradoException("Módulo não encontrado!")), Modulo.class);

        Aula aula = modelMapper.map(dto, Aula.class);
        aula.setCurso(curso);
        aula.setModulo(modulo);

        List<Usuario> usuarios = usuarioRepository.findAll();

        for (Usuario u : usuarios) {
            progressoAulaRepository.save(new ProgressoAula(null, u, aula, Boolean.FALSE));
        }
        return modelMapper.map(aulaRepository.save(aula), AulaDTOOut.class);
    }

    @Transactional
    public AulaDTOOut update(Long id, AulaDTOIn dto) {
        Aula aula = aulaRepository.getReferenceById(id);
        aula.setCurso(cursoRepository.findById(dto.getCurso()).orElseThrow(() -> new RecursoNaoEncontradoException(dto.getCurso())));
        aula.setModulo(moduloRepository.findById(dto.getModulo()).orElseThrow(() -> new RecursoNaoEncontradoException(dto.getModulo())));
        aula.setTitulo(dto.getTitulo());
        aula.setDescricao(dto.getDescricao());
        aula.setOrdem(dto.getOrdem());

        return modelMapper.map(aulaRepository.save(aula), AulaDTOOut.class);
    }

    @Transactional
    public AulaDTOOut marcarComoAssistida(Long idAula, Long idUsuario) {
        ProgressoAula progressoAula = progressoAulaRepository.findByIdAulaAndIdUsuario(idAula, idUsuario).orElseThrow(() -> new RecursoNaoEncontradoException(idAula));
        progressoAula.setAssistida(Boolean.TRUE);

        return modelMapper.map(progressoAula.getAula(), AulaDTOOut.class);
    }

    public void delete(Long idCurso, Long idModulo, Integer ordem) {
        AulaDTOOut aula = findByCourseModuleAndOrder(idCurso, idModulo, ordem);
        if (aula != null) {
            aulaRepository.delete(modelMapper.map(aula, Aula.class));
        }
    }

    public List<AulaDTOOut> findAllByModule(Long id) {
        return aulaRepository.findAllByModule(id).stream().map(a -> modelMapper.map(a, AulaDTOOut.class)).toList();
    }
}
