package br.com.educatech.EducaTech.controllers;

import br.com.educatech.EducaTech.dtos.modulo.ModuloComAulasDTOOut;
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
    public ResponseEntity<List<ModuloDTOOut>> buscarTodos(@RequestParam(required = false) String titulo,
                                                      @RequestParam(required = false) Long idCurso) {
        return ResponseEntity.ok(moduloService.buscarTodos(titulo, idCurso));
    }

    @GetMapping(value = "/curso/{idCurso}")
    public ResponseEntity<List<ModuloDTOOut>> buscarTodosPorCurso(@PathVariable Long idCurso) {
        return ResponseEntity.ok(moduloService.buscarTodosPorCurso(idCurso));
    }

    @GetMapping(value = "/aulas/{idCurso}/{idUsuario}")
    public ResponseEntity<List<ModuloComAulasDTOOut>> buscarTodosComAulasPorIdCurso(@PathVariable Long idCurso,
                                                                                   @PathVariable Long idUsuario) {
        return ResponseEntity.ok(moduloService.buscarTodosComAulasPorIdCurso(idCurso, idUsuario));
    }

    @GetMapping(value = "/proxima-ordem/{idCurso}")
    public ResponseEntity<Integer> buscarProximaOrdem(@PathVariable Long idCurso) throws Exception {
        return ResponseEntity.ok(moduloService.buscarProximaOrdem(idCurso));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ModuloDTOOut> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(moduloService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ModuloDTOOut> inserir(@RequestBody @Valid ModuloDTOIn dto) {
        return ResponseEntity.ok(moduloService.inserir(dto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ModuloDTOOut> editar(@PathVariable Long id, @RequestBody @Valid ModuloDTOIn dto) {
        return ResponseEntity.ok(moduloService.editar(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        moduloService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
