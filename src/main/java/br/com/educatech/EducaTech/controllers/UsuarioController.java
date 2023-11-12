package br.com.educatech.EducaTech.controllers;

import br.com.educatech.EducaTech.dtos.UsuarioDTOIn;
import br.com.educatech.EducaTech.dtos.UsuarioDTOOut;
import br.com.educatech.EducaTech.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioDTOOut>> findAllPaged(@RequestParam(required = false) String cpf,
                                                            @RequestParam(required = false) String nome,
                                                            Pageable pageable) {
        return ResponseEntity.ok(usuarioService.findAllPaged(cpf, nome, pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTOOut> findById(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioDTOOut> insert(@RequestBody @Valid UsuarioDTOIn dto) {
        return ResponseEntity.ok(usuarioService.insert(dto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTOOut> update(@PathVariable Long id, @RequestBody @Valid UsuarioDTOIn dto) {
        return ResponseEntity.ok(usuarioService.update(id, dto));
    }
}
