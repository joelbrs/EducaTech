package br.com.educatech.EducaTech.services;

import br.com.educatech.EducaTech.dtos.curso.CursoDTOOut;
import br.com.educatech.EducaTech.dtos.modulo.ModuloDTOIn;
import br.com.educatech.EducaTech.dtos.modulo.ModuloDTOOut;
import br.com.educatech.EducaTech.model.Curso;
import br.com.educatech.EducaTech.model.Modulo;
import br.com.educatech.EducaTech.repositories.ModuloRepository;
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
public class ModuloService {

    private final ModuloRepository moduloRepository;
    private final ModelMapper modelMapper;
    private final CursoService cursoService;

    public ModuloService(ModuloRepository moduloRepository, ModelMapper modelMapper, CursoService cursoService) {
        this.moduloRepository = moduloRepository;
        this.modelMapper = modelMapper;
        this.cursoService = cursoService;
    }

    @Transactional(readOnly = true)
    public List<ModuloDTOOut> findAll() {
        List<Modulo> modulos = moduloRepository.findAll();

        return modulos.stream().map(m -> modelMapper.map(m, ModuloDTOOut.class)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<ModuloDTOOut> findAllPaged(String titulo, Pageable pageable) {
        Page<Modulo> modulos = moduloRepository.findAllPaged(titulo, pageable);
        return modulos.map(m -> modelMapper.map(m, ModuloDTOOut.class));
    }

    @Transactional(readOnly = true)
    public List<ModuloDTOOut> findAllByCourse(Long id) {
        List<Modulo> modulos = moduloRepository.findAllByIdCourse(id);
        return modulos.stream().map(m -> modelMapper.map(m, ModuloDTOOut.class)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Integer findNextOrder() throws Exception {
        Modulo modulo = moduloRepository.findModuleWithMaxOrder().orElseThrow(Exception::new);
        return modulo.getOrdem() + 1;
    }

    public ModuloDTOOut findById(Long id) {
        Modulo modulo = moduloRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException(id));
        return modelMapper.map(modulo, ModuloDTOOut.class);
    }

    @Transactional
    public ModuloDTOOut insert(ModuloDTOIn dto) {
        Curso curso = modelMapper.map(cursoService.findById(dto.getCurso()), Curso.class);
        Modulo modulo = modelMapper.map(dto, Modulo.class);
        modulo.setCurso(curso);

        return modelMapper.map(moduloRepository.save(modulo), ModuloDTOOut.class);
    }

    @Transactional
    public ModuloDTOOut update(Long id, ModuloDTOIn dto) {
        try {
            Modulo modulo = moduloRepository.getReferenceById(id);
            modulo.setTitulo(dto.getTitulo());
            modulo.setDescricao(dto.getDescricao());

            return modelMapper.map(modulo, ModuloDTOOut.class);
        }
        catch (EntityNotFoundException e) {
            throw new RecursoNaoEncontradoException(id);
        }
    }

    public void delete(Long id) {
        try {
            Modulo modulo = moduloRepository.getReferenceById(id);
            moduloRepository.delete(modulo);
        }
        catch (EntityNotFoundException e) {
            throw new RecursoNaoEncontradoException(id);
        }
    }
}
