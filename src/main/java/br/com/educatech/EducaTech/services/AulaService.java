package br.com.educatech.EducaTech.services;

import br.com.educatech.EducaTech.dtos.aula.AulaDTOIn;
import br.com.educatech.EducaTech.dtos.aula.AulaDTOOut;
import br.com.educatech.EducaTech.model.Aula;
import br.com.educatech.EducaTech.model.Curso;
import br.com.educatech.EducaTech.model.Modulo;
import br.com.educatech.EducaTech.repositories.AulaRepository;
import br.com.educatech.EducaTech.repositories.ModuloRepository;
import br.com.educatech.EducaTech.services.exceptions.RecursoNaoEncontradoException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AulaService {

    private final AulaRepository aulaRepository;
    private final ModelMapper modelMapper;
    private final CursoService cursoService;
    private final ModuloRepository moduloRepository;

    public AulaService(AulaRepository aulaRepository, ModelMapper modelMapper, CursoService cursoService, ModuloRepository moduloRepository) {
        this.aulaRepository = aulaRepository;
        this.modelMapper = modelMapper;
        this.cursoService = cursoService;
        this.moduloRepository = moduloRepository;
    }

    @Transactional(readOnly = true)
    public List<AulaDTOOut> findAll() {
        List<Aula> aulas = aulaRepository.findAll();
        return aulas.stream().map(a -> modelMapper.map(a, AulaDTOOut.class)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<AulaDTOOut> findAllPaged(String titulo, Pageable pageable) {
        Page<Aula> aulas = aulaRepository.findAllPaged(titulo, pageable);
        return aulas.map(a -> modelMapper.map(a, AulaDTOOut.class));
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
                    Aula aula = new Aula(a.getCurso(), a.getModulo(), a.getTitulo(), a.getDescricao(), a.getAssistida(), a.getOrdem());
                    return modelMapper.map(aula, AulaDTOOut.class);
                }
            }
        }
        throw new RecursoNaoEncontradoException("Aula não encontrada!");
    }

    @Transactional(readOnly = true)
    public Integer findNextOrder() throws Exception {
        Aula aula = aulaRepository.findAulaWithMaxOrder().orElseThrow(Exception::new);
        return aula.getOrdem() + 1;
    }


    @Transactional
    public AulaDTOOut insert(AulaDTOIn dto) {
        Curso curso = modelMapper.map(cursoService.findById(dto.getCurso()), Curso.class);
        Modulo modulo = modelMapper.map(moduloRepository.findById(dto.getModulo()).orElseThrow(()->new RecursoNaoEncontradoException("Módulo não encontrado!")), Modulo.class);

        Aula aula = modelMapper.map(dto, Aula.class);
        aula.setCurso(curso);
        aula.setModulo(modulo);
        aula.setAssistida(Boolean.FALSE);
        return modelMapper.map(aulaRepository.save(aula), AulaDTOOut.class);
    }

    @Transactional
    public AulaDTOOut update(Long idCurso, Long idModulo, Integer ordem, AulaDTOIn dto) {
        Curso curso = modelMapper.map(cursoService.findById(dto.getCurso()), Curso.class);
        Modulo modulo = modelMapper.map(moduloRepository.findById(dto.getModulo()).orElseThrow(()->new RecursoNaoEncontradoException("Módulo não encontrado!")), Modulo.class);

        Aula aula = modelMapper.map(findByCourseModuleAndOrder(idCurso, idModulo, ordem), Aula.class);
        aula.setCurso(curso);
        aula.setModulo(modulo);
        aula.setTitulo(dto.getTitulo());
        aula.setDescricao(dto.getDescricao());
        aula.setOrdem(dto.getOrdem());

        return modelMapper.map(aulaRepository.save(aula), AulaDTOOut.class);
    }

    public void delete(Long idCurso, Long idModulo, Integer ordem) {
        Aula aula = modelMapper.map(findByCourseModuleAndOrder(idCurso, idModulo, ordem), Aula.class);
        aulaRepository.delete(aula);
    }
}
