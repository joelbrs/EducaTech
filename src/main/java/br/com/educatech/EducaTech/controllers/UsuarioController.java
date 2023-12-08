package br.com.educatech.EducaTech.controllers;

import br.com.educatech.EducaTech.dtos.usuario.UsuarioDTOIn;
import br.com.educatech.EducaTech.dtos.usuario.UsuarioDTOOut;
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

    @GetMapping("/login")
    public ResponseEntity<UsuarioDTOOut> login(@RequestParam(required = true) String email,
                                      @RequestParam(required = true) String senha) throws Exception {
        return ResponseEntity.ok(usuarioService.login(email, senha));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTOOut> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioDTOOut> inserir(@RequestBody @Valid UsuarioDTOIn dto) {
        return ResponseEntity.ok(usuarioService.inserir(dto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTOOut> editar(@PathVariable Long id, @RequestBody @Valid UsuarioDTOIn dto) throws Exception {
        return ResponseEntity.ok(usuarioService.editar(id, dto));
    }
}
