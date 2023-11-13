package br.com.educatech.EducaTech.controllers;

import br.com.educatech.EducaTech.dtos.modulo.ModuloDTOIn;
import br.com.educatech.EducaTech.dtos.modulo.ModuloDTOOut;
import br.com.educatech.EducaTech.services.ModuloService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/modulos")
public class ModuloController {

    private final ModuloService moduloService;

    public ModuloController(ModuloService moduloService) {
        this.moduloService = moduloService;
    }

    @GetMapping
    public ResponseEntity<Page<ModuloDTOOut>> findAllPaged(@RequestParam(required = false) String titulo,
                                                          Pageable pageable) {
        return ResponseEntity.ok(moduloService.findAllPaged(titulo, pageable));
    }

    @GetMapping(value = "/select")
    public ResponseEntity<List<ModuloDTOOut>> findAll() {
        return ResponseEntity.ok(moduloService.findAll());
    }

    @GetMapping(value = "/curso/{idCurso}")
    public ResponseEntity<List<ModuloDTOOut>> findAllByCourse(@PathVariable Long idCurso) {
        return ResponseEntity.ok(moduloService.findAllByCourse(idCurso));
    }

    @GetMapping(value = "/next-order")
    public ResponseEntity<Integer> findNextOrder() throws Exception {
        return ResponseEntity.ok(moduloService.findNextOrder());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ModuloDTOOut> findById(@PathVariable Long id) {
        return ResponseEntity.ok(moduloService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ModuloDTOOut> insert(@RequestBody @Valid ModuloDTOIn dto) {
        return ResponseEntity.ok(moduloService.insert(dto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ModuloDTOOut> update(@PathVariable Long id, @RequestBody @Valid ModuloDTOIn dto) {
        return ResponseEntity.ok(moduloService.update(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        moduloService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
