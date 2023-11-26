package br.com.educatech.EducaTech.services;

import br.com.educatech.EducaTech.dtos.curso.CursoDTOIn;
import br.com.educatech.EducaTech.dtos.curso.CursoDTOOut;
import br.com.educatech.EducaTech.dtos.modulo.ModuloDTOOut;
import br.com.educatech.EducaTech.model.Curso;
import br.com.educatech.EducaTech.model.Modulo;
import br.com.educatech.EducaTech.repositories.AulaRepository;
import br.com.educatech.EducaTech.repositories.CursoRepository;
import br.com.educatech.EducaTech.services.exceptions.RecursoNaoEncontradoException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;
    private final ModelMapper modelMapper;
    private final ModuloService moduloService;
    private final AulaRepository aulaRepository;

    public CursoService(CursoRepository cursoRepository, ModelMapper modelMapper, ModuloService moduloService, AulaRepository aulaRepository) {
        this.cursoRepository = cursoRepository;
        this.modelMapper = modelMapper;
        this.moduloService = moduloService;
        this.aulaRepository = aulaRepository;
    }

    @Transactional(readOnly = true)
    public List<CursoDTOOut> findAll(String titulo) {
        List<Curso> cursos = cursoRepository.findAll();
        List<CursoDTOOut> dtos = cursos.stream().map(c -> modelMapper.map(c, CursoDTOOut.class)).toList();

        for (CursoDTOOut c : dtos) {
            List<ModuloDTOOut> modulos = moduloService.findAllByCourse(c.getId());
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

    public CursoDTOOut findById(Long id) {
        Curso curso = cursoRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException(id));
        CursoDTOOut dto = modelMapper.map(curso, CursoDTOOut.class);

        List<ModuloDTOOut> modulos = moduloService.findAllByCourse(id);
        dto.setQtdModulos(modulos.size());

        for (ModuloDTOOut m : modulos) {
            dto.setQtdAulas(dto.getQtdAulas() + aulaRepository.findAllByCourseAndModule(dto.getId(), m.getId()).size());
        }
        return dto;
    }

    @Transactional
    public CursoDTOOut insert(CursoDTOIn dto) {
        Curso curso = cursoRepository.save(modelMapper.map(dto, Curso.class));
        return modelMapper.map(curso, CursoDTOOut.class);
    }

    @Transactional
    public CursoDTOOut update(Long id, CursoDTOIn dto) {
        try {
            Curso curso = cursoRepository.getReferenceById(id);
            curso.setTitulo(dto.getTitulo());
            curso.setCargaHoraria(dto.getCargaHoraria());
            curso.setImagem(dto.getImagem());

            return modelMapper.map(cursoRepository.save(curso), CursoDTOOut.class);
        }
        catch (EntityNotFoundException e) {
            throw new RecursoNaoEncontradoException(id);
        }
    }

    public void delete(Long id) {
        try {
            Curso curso = cursoRepository.getReferenceById(id);
            List<ModuloDTOOut> modulos = moduloService.findAllByCourse(id);

            for (ModuloDTOOut m : modulos) {
                moduloService.delete(m.getId());
            }

            cursoRepository.delete(curso);
        }
        catch (EntityNotFoundException e) {
            throw new RecursoNaoEncontradoException(id);
        }
    }
}
