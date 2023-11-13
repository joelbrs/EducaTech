package br.com.educatech.EducaTech.controllers;

import br.com.educatech.EducaTech.dtos.curso.CursoDTOIn;
import br.com.educatech.EducaTech.dtos.curso.CursoDTOOut;
import br.com.educatech.EducaTech.services.CursoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping(value = "/select")
    public ResponseEntity<List<CursoDTOOut>> findAll() {
        return ResponseEntity.ok(cursoService.findAll());
    }

    @GetMapping
    public ResponseEntity<Page<CursoDTOOut>> findAllPaged(@RequestParam(required = false) String nome,
                                                          Pageable pageable) {
        return ResponseEntity.ok(cursoService.findAllPaged(nome, pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CursoDTOOut> findById(@PathVariable Long id) {
        return ResponseEntity.ok(cursoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CursoDTOOut> insert(@RequestBody @Valid CursoDTOIn dto) {
        return ResponseEntity.ok(cursoService.insert(dto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CursoDTOOut> update(@PathVariable Long id, @RequestBody @Valid CursoDTOIn dto) {
        return ResponseEntity.ok(cursoService.update(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
       cursoService.delete(id);

       return ResponseEntity.noContent().build();
    }
}
