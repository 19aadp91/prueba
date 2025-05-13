package com.example.prueba.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prueba.domain.autores.BusinessLogic.IAutorService;
import com.example.prueba.domain.autores.dtos.CreateAutor;
import com.example.prueba.domain.autores.dtos.GetAutor;


@RestController
@RequestMapping("/api/autores")
public class autoresController {
    private final IAutorService _autorService;

    public autoresController(IAutorService autorService) {
        this._autorService = autorService;
    }

    @PostMapping
    public ResponseEntity<String> crear(@RequestBody CreateAutor autor) {
        this._autorService.insertarAutor(autor);
        return ResponseEntity.ok("Autor creado");
    }

    @PutMapping
    public ResponseEntity<String> actualizar(@RequestBody GetAutor autor) {
        this._autorService.actualizarAutor(autor);
        return ResponseEntity.ok("Autor actualizado");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        this._autorService.eliminarAutor(id);
        return ResponseEntity.ok("Autor eliminado");
    }

    @GetMapping
    public ResponseEntity<List<GetAutor>> listar() {
        return ResponseEntity.ok(this._autorService.listarAutores());
    }
}
