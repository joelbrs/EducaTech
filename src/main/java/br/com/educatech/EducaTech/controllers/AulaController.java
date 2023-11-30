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
    public ResponseEntity<List<AulaDTOOut>> findAll(@RequestParam(required = false) String titulo,
                                                    @RequestParam(required = false) Long idCurso,
                                                    @RequestParam(required = false) Long idModulo) {
        return ResponseEntity.ok(aulaService.findAll(titulo, idCurso, idModulo));
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

    @GetMapping(value = "/proxima-ordem/{idCurso}/{idModulo}")
    public ResponseEntity<Integer> findNextOrder(@PathVariable Long idCurso,
                                                 @PathVariable Long idModulo) {
        return ResponseEntity.ok(aulaService.findNextOrder(idCurso, idModulo));
    }

    @PostMapping
    public ResponseEntity<AulaDTOOut> insert(@RequestBody @Valid AulaDTOIn dto) {
        return ResponseEntity.ok(aulaService.insert(dto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AulaDTOOut> update(@PathVariable Long id,
                                             @RequestBody AulaDTOIn dto) {
        return ResponseEntity.ok(aulaService.update(id, dto));
    }

    @PutMapping(value = "/assistida/{idAula}/{idUsuario}")
    public ResponseEntity<AulaDTOOut> marcarComoAssistida(@PathVariable Long idAula,
                                                          @PathVariable Long idUsuario) {
        return ResponseEntity.ok(aulaService.marcarComoAssistida(idAula, idUsuario));
    }

    @DeleteMapping(value = "/{idCurso}/{idModulo}/{ordem}")
    public ResponseEntity<Void> delete(@PathVariable Long idCurso,
                                       @PathVariable Long idModulo,
                                       @PathVariable Integer ordem) {
        aulaService.delete(idCurso, idModulo, ordem);
        return ResponseEntity.noContent().build();
    }
}
