package com.fondoseguro.controller;

import com.fondoseguro.model.Usuario;
import com.fondoseguro.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuarios")
@CrossOrigin(origins = "*") // Clave para que el Frontend no falle por CORS
public class UsuarioController {

    private final UsuarioRepository repository;

    public UsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<String> registrar(@RequestBody Usuario usuario) {
        repository.crearOActualizar(usuario);
        return ResponseEntity.ok("Usuario guardado en la nube");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtener(@PathVariable String id) {
        Usuario user = repository.obtenerPorId(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrar(@PathVariable String id) {
        repository.eliminar(id);
        return ResponseEntity.ok("Perfil eliminado");
    }
}