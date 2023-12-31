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
import br.com.educatech.EducaTech.utils.PadraoService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Camada de Serviço da Entidade Módulo, responsável pelas regras de negócio da aplicação em relação a essa Entidade
 * */
@Service
public class ModuloService implements PadraoService<ModuloDTOIn, ModuloDTOOut> {

    /**
     * Injeção de Dependências
     * */
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

    @Override
    public List<ModuloDTOOut> buscarTodos() {
        return moduloRepository.findAll().stream().map(m -> modelMapper.map(m, ModuloDTOOut.class)).toList();
    }

    /**
     * Método que busca todas os Módulos filtrando por Título e/ou Curso
     * */
    @Transactional(readOnly = true)
    public List<ModuloDTOOut> buscarTodos(String titulo, Long idCurso) {
        List<ModuloDTOOut> modulos = moduloRepository.findAll().stream().map(m -> modelMapper.map(m, ModuloDTOOut.class)).toList();

        if (titulo != null && !titulo.isBlank()) {
            modulos = modulos.stream().filter(c -> c.getTitulo().toLowerCase().contains(titulo.toLowerCase())).toList();
        }

        if (idCurso != null) {
            return modulos.stream().filter(c -> c.getCurso().getId().equals(idCurso)).toList();
        }
        return modulos;
    }

    /**
     * Método que buscar todos os módulos de um curso que possuem aulas cadastradas
     * */
    @Transactional(readOnly = true)
    public List<ModuloComAulasDTOOut> buscarTodosComAulasPorIdCurso(Long idCourse, Long idUsuario) {
        List<ModuloComAulasDTOOut> modulos = buscarTodos(null, null).stream().map(m -> modelMapper.map(m, ModuloComAulasDTOOut.class)).toList();

        for (ModuloComAulasDTOOut m : modulos) {
            List<AulaDTOOut> aulas = aulaService.buscarPorCursoeModulo(idCourse, m.getId());
            m.setAulas(aulas.stream().map(a -> {
                try {
                    return new AulaSemModuloDTOOut(a, aulaService.verificarAulaAssistida(a.getId(), idUsuario));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }).toList());
        }
        return modulos;
    }

    /**
     * Método que busca todos os Módulos por Curso
     * */
    @Transactional(readOnly = true)
    public List<ModuloDTOOut> buscarTodosPorCurso(Long id) {
        List<Modulo> modulos = moduloRepository.findAllByIdCourse(id);
        return modulos.stream().map(m -> modelMapper.map(m, ModuloDTOOut.class)).collect(Collectors.toList());
    }

    /**
     * Método que busca a próxima ordem de um curso
     * */
    @Transactional(readOnly = true)
    public Integer buscarProximaOrdem(Long idCurso) {
        Modulo modulo = moduloRepository.findModuleWithMaxOrder(idCurso).orElse(null);

        if (modulo == null) {
            return 1;
        }
        return modulo.getOrdem() + 1;
    }

    /**
     * Método que busca um Módulo pelo seu identificador único
     * */
    public ModuloDTOOut buscarPorId(Long id) throws Exception {
        Modulo modulo = moduloRepository.findById(id).orElseThrow(() -> new Exception("Módulo não encontrado, id: "+ id));

        return modelMapper.map(modulo, ModuloDTOOut.class);
    }

    @Override
    @Transactional
    public ModuloDTOOut inserir(ModuloDTOIn dto) throws Exception {
        Curso curso = modelMapper.map(cursoRepository.findById(dto.getCurso()).orElseThrow(() -> new Exception("Módulo não encontrado")), Curso.class);
        Modulo modulo = modelMapper.map(dto, Modulo.class);
        modulo.setCurso(curso);

        if (dto.getMaterial() != null) {
            modulo.setMaterial(materialRepository.save(modelMapper.map(dto.getMaterial(), Material.class)));
        }

        return modelMapper.map(moduloRepository.save(modulo), ModuloDTOOut.class);
    }

    @Override
    @Transactional
    public ModuloDTOOut editar(Long id, ModuloDTOIn dto) throws Exception {
        try {
            Modulo modulo = moduloRepository.getReferenceById(id);
            modulo.setTitulo(dto.getTitulo());
            modulo.setDescricao(dto.getDescricao());

            return modelMapper.map(modulo, ModuloDTOOut.class);
        }
        catch (EntityNotFoundException e) {
            throw new Exception("Módulo não encontrado, id: "+ id);
        }
    }

    public void delete(Long id) throws Exception {
        try {
            moduloRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new Exception("Módulo não encontrado, id: "+ id);
        }
    }
}
