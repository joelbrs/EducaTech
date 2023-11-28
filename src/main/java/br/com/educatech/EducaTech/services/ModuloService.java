package br.com.educatech.EducaTech.services;

import br.com.educatech.EducaTech.dtos.aula.AulaDTOOut;
import br.com.educatech.EducaTech.dtos.aula.AulaSemModuloDTOOut;
import br.com.educatech.EducaTech.dtos.modulo.ModuloComAulasDTOOut;
import br.com.educatech.EducaTech.dtos.modulo.ModuloDTOIn;
import br.com.educatech.EducaTech.dtos.modulo.ModuloDTOOut;
import br.com.educatech.EducaTech.model.Curso;
import br.com.educatech.EducaTech.model.Material;
import br.com.educatech.EducaTech.model.Modulo;
import br.com.educatech.EducaTech.repositories.CursoRepository;
import br.com.educatech.EducaTech.repositories.MaterialRepository;
import br.com.educatech.EducaTech.repositories.ModuloRepository;
import br.com.educatech.EducaTech.services.exceptions.RecursoNaoEncontradoException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModuloService {

    private final ModuloRepository moduloRepository;
    private final ModelMapper modelMapper;
    private final CursoRepository cursoRepository;
    private final AulaService aulaService;
    private final MaterialRepository materialRepository;

    public ModuloService(ModuloRepository moduloRepository, ModelMapper modelMapper, CursoRepository cursoRepository, AulaService aulaService, MaterialRepository materialRepository) {
        this.moduloRepository = moduloRepository;
        this.modelMapper = modelMapper;
        this.cursoRepository = cursoRepository;
        this.aulaService = aulaService;
        this.materialRepository = materialRepository;
    }

    @Transactional(readOnly = true)
    public List<ModuloDTOOut> findAll(String titulo, Long idCurso) {
        List<ModuloDTOOut> modulos = moduloRepository.findAll().stream().map(m -> modelMapper.map(m, ModuloDTOOut.class)).toList();

        if (titulo != null && !titulo.isBlank()) {
            modulos = modulos.stream().filter(c -> c.getTitulo().toLowerCase().contains(titulo.toLowerCase())).toList();
        }

        if (idCurso != null) {
            return modulos.stream().filter(c -> c.getCurso().getId().equals(idCurso)).toList();
        }
        return modulos;
    }

    @Transactional(readOnly = true)
    public List<ModuloComAulasDTOOut> findAllByIdCourseWithClasses(Long idCourse) {
        List<ModuloComAulasDTOOut> modulos = findAll(null, null).stream().map(m -> modelMapper.map(m, ModuloComAulasDTOOut.class)).toList();

        for (ModuloComAulasDTOOut m : modulos) {
            m.setAulas(aulaService.findAllByCourseAndModule(idCourse, m.getId()).stream().map(AulaSemModuloDTOOut::new).toList());
        }
        return modulos;
    }

    @Transactional(readOnly = true)
    public List<ModuloDTOOut> findAllByCourse(Long id) {
        List<Modulo> modulos = moduloRepository.findAllByIdCourse(id);
        return modulos.stream().map(m -> modelMapper.map(m, ModuloDTOOut.class)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Integer findNextOrder(Long idCurso) {
        Modulo modulo = moduloRepository.findModuleWithMaxOrder(idCurso).orElse(null);

        if (modulo == null) {
            return 1;
        }
        return modulo.getOrdem() + 1;
    }

    public ModuloDTOOut findById(Long id) {
        Modulo modulo = moduloRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException(id));

        return modelMapper.map(modulo, ModuloDTOOut.class);
    }

    @Transactional
    public ModuloDTOOut insert(ModuloDTOIn dto) {
        Curso curso = modelMapper.map(cursoRepository.findById(dto.getCurso()).orElseThrow(() -> new RecursoNaoEncontradoException("Curso não encontrado!")), Curso.class);
        Modulo modulo = modelMapper.map(dto, Modulo.class);
        modulo.setCurso(curso);

        if (dto.getMaterial() != null) {
            modulo.setMaterial(materialRepository.save(modelMapper.map(dto.getMaterial(), Material.class)));
        }

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
            List<AulaDTOOut> aulas = aulaService.findAllByCourseAndModule(modulo.getCurso().getId(), id);

            for (AulaDTOOut a : aulas) {
                aulaService.delete(a.getCurso().getId(), a.getModulo().getId(), a.getOrdem());
            }

            moduloRepository.delete(modulo);
        }
        catch (EntityNotFoundException e) {
            throw new RecursoNaoEncontradoException(id);
        }
    }
}
