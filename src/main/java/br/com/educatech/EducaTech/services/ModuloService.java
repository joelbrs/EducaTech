package br.com.educatech.EducaTech.services;

import br.com.educatech.EducaTech.dtos.modulo.ModuloDTOIn;
import br.com.educatech.EducaTech.dtos.modulo.ModuloDTOOut;
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

    public ModuloService(ModuloRepository moduloRepository, ModelMapper modelMapper) {
        this.moduloRepository = moduloRepository;
        this.modelMapper = modelMapper;
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
        Modulo modulo = moduloRepository.save(modelMapper.map(dto, Modulo.class));
        return modelMapper.map(modulo, ModuloDTOOut.class);
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
