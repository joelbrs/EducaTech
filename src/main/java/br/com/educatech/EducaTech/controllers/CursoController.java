package br.com.educatech.EducaTech.controllers;

import br.com.educatech.EducaTech.dtos.curso.CursoDTOIn;
import br.com.educatech.EducaTech.dtos.curso.CursoDTOOut;
import br.com.educatech.EducaTech.dtos.curso.certificado.ModeloCertificadoDTO;
import br.com.educatech.EducaTech.dtos.curso.certificado.ModeloCertificadoRequestDTO;
import br.com.educatech.EducaTech.services.CursoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public ResponseEntity<List<CursoDTOOut>> findAll(String titulo) {
        return ResponseEntity.ok(cursoService.buscarTodosFiltro(titulo));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CursoDTOOut> findById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(cursoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<CursoDTOOut> insert(@RequestBody @Valid CursoDTOIn dto) {
        return ResponseEntity.ok(cursoService.inserir(dto));
    }

    @PostMapping(value = "/emitir-certificado")
    public ResponseEntity<ModeloCertificadoDTO> emitirCertificado(@RequestBody ModeloCertificadoRequestDTO dto) throws Exception {
        return ResponseEntity.ok(cursoService.emitirCertificado(dto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CursoDTOOut> editar(@PathVariable Long id, @RequestBody @Valid CursoDTOIn dto) throws Exception {
        return ResponseEntity.ok(cursoService.editar(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {
       cursoService.delete(id);

       return ResponseEntity.noContent().build();
    }
}
