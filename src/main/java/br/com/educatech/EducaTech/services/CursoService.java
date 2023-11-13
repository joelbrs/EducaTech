package br.com.educatech.EducaTech.services;

import br.com.educatech.EducaTech.dtos.curso.CursoDTOIn;
import br.com.educatech.EducaTech.dtos.curso.CursoDTOOut;
import br.com.educatech.EducaTech.model.Curso;
import br.com.educatech.EducaTech.repositories.CursoRepository;
import br.com.educatech.EducaTech.services.exceptions.RecursoNaoEncontradoException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;
    private final ModelMapper modelMapper;

    public CursoService(CursoRepository cursoRepository, ModelMapper modelMapper) {
        this.cursoRepository = cursoRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional(readOnly = true)
    public List<CursoDTOOut> findAll() {
        List<Curso> cursos = cursoRepository.findAll();
        return cursos.stream().map(c -> modelMapper.map(c, CursoDTOOut.class)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<CursoDTOOut> findAllPaged(String nome, Pageable pageable) {
        Page<Curso> cursos = cursoRepository.findAllPaged(nome, pageable);
        return cursos.map(c -> modelMapper.map(c, CursoDTOOut.class));
    }

    public CursoDTOOut findById(Long id) {
        Curso curso = cursoRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException(id));
        return modelMapper.map(curso, CursoDTOOut.class);
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

            cursoRepository.delete(curso);
        }
        catch (EntityNotFoundException e) {
            throw new RecursoNaoEncontradoException(id);
        }
    }
}
