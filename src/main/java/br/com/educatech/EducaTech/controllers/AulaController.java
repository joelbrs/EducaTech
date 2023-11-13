package br.com.educatech.EducaTech.controllers;

import br.com.educatech.EducaTech.dtos.aula.AulaDTOIn;
import br.com.educatech.EducaTech.dtos.aula.AulaDTOOut;
import br.com.educatech.EducaTech.services.AulaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/aulas")
public class AulaController {

    private final AulaService aulaService;

    public AulaController(AulaService aulaService) {
        this.aulaService = aulaService;
    }

    @GetMapping
    public ResponseEntity<Page<AulaDTOOut>> findAllPaged(@RequestParam(required = false) String titulo,
                                                         Pageable pageable) {
        return ResponseEntity.ok(aulaService.findAllPaged(titulo, pageable));
    }

    @GetMapping(value = "/select")
    public ResponseEntity<List<AulaDTOOut>> findAll() {
        return ResponseEntity.ok(aulaService.findAll());
    }

    @GetMapping(value = "/{idCurso}/{idModulo}")
    public ResponseEntity<List<AulaDTOOut>> findAllByCourseAndModule(@PathVariable Long idCurso, @PathVariable Long idModulo) {
        return ResponseEntity.ok(aulaService.findAllByCourseAndModule(idCurso, idModulo));
    }

    @GetMapping(value = "/{idCurso}/{idModulo}/{ordem}")
    public ResponseEntity<AulaDTOOut> findById(@PathVariable Long idCurso,
                                               @PathVariable Long idModulo,
                                               @PathVariable Integer ordem) {
        return ResponseEntity.ok(aulaService.findByCourseModuleAndOrder(idCurso, idModulo, ordem));
    }

    @GetMapping(value = "/next-order")
    public ResponseEntity<Integer> findNextOrder() throws Exception {
        return ResponseEntity.ok(aulaService.findNextOrder());
    }

    @PostMapping
    public ResponseEntity<AulaDTOOut> insert(@RequestBody @Valid AulaDTOIn dto) {
        return ResponseEntity.ok(aulaService.insert(dto));
    }

    @PutMapping(value = "/{idCurso}/{idModulo}/{ordem}")
    public ResponseEntity<AulaDTOOut> update(@PathVariable Long idCurso,
                                             @PathVariable Long idModulo,
                                             @PathVariable Integer ordem,
                                             @RequestBody AulaDTOIn dto) {
        return ResponseEntity.ok(aulaService.update(idCurso, idModulo, ordem, dto));
    }

    @DeleteMapping(value = "/{idCurso}/{idModulo}/{ordem}")
    public ResponseEntity<Void> delete(@PathVariable Long idCurso,
                                       @PathVariable Long idModulo,
                                       @PathVariable Integer ordem) {
        aulaService.delete(idCurso, idModulo, ordem);
        return ResponseEntity.noContent().build();
    }
}
