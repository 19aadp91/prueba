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

import com.example.prueba.domain.libros.BusinessLogic.ILibroService;
import com.example.prueba.domain.libros.dtos.CreateLibro;
import com.example.prueba.domain.libros.dtos.GetLibro;
import com.example.prueba.domain.libros.dtos.UpdateLibro;

@RestController
@RequestMapping("/api/Libro")
public class LibroController {

    private final ILibroService _libroService;

    public LibroController(ILibroService libroService) {
        this._libroService = libroService;
    }

    @PostMapping
    public ResponseEntity<String> crear(@RequestBody CreateLibro libro) {
        this._libroService.crearLibro(libro);
        return ResponseEntity.ok("Libro creado");
    }

    @PutMapping
    public ResponseEntity<String> actualizar(@RequestBody UpdateLibro libro) {
        this._libroService.actualizarLibro(libro);
        return ResponseEntity.ok("Libro actualizado");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        this._libroService.eliminarLibro(id);
        return ResponseEntity.ok("Libro eliminado");
    }

    @GetMapping
    public ResponseEntity<List<GetLibro>> listar() {
        return ResponseEntity.ok(this._libroService.listarLibros());
    }
}
