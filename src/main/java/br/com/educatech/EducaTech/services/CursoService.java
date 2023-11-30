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
import br.com.educatech.EducaTech.services.exceptions.RecursoNaoEncontradoException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class CursoService {

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
        Curso curso = modelMapper.map(dto, Curso.class);

        Certificado certificado = certificadoRepository.save(new Certificado(dto.getModeloCertificado().getNome(), dto.getModeloCertificado().getArquivo(), curso));
        curso.setModeloCertificado(certificado);

        List<Usuario> usuarios = usuarioRepository.findAll();
        for (Usuario u : usuarios) {
            progressoCursoRepository.save(new ProgressoCurso(u, curso));
        }
        return modelMapper.map(cursoRepository.save(curso), CursoDTOOut.class);
    }

    @Transactional
    public ModeloCertificadoDTO emitirCertificado(ModeloCertificadoRequestDTO req) {
        CursoDTOOut dto = finalizar(req.getIdCurso(), req.getIdUsuario());

        return modelMapper.map(dto.getCertificado(), ModeloCertificadoDTO.class);
    }

    @Transactional
    public CursoDTOOut finalizar(Long idCurso, Long idUsuario) {
        ProgressoCurso progresso = progressoCursoRepository.findByIdCourseAndIdUser(idCurso, idUsuario).orElseThrow(() -> new RecursoNaoEncontradoException("Progresso nao encontrado! idCurso: " + idCurso + ", idUsuario: " + idUsuario));
        progresso.setStatusCurso(StatusCurso.COMPLETO.getCode());
        progresso.setDataConclusao(Instant.now());

        progressoCursoRepository.save(progresso);
        return findById(idCurso);
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
