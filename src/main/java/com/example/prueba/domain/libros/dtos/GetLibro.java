package com.example.prueba.domain.libros.dtos;

public record GetLibro(
    Long id,
    String titulo,
    Long autorId,
    String autorNombre
) {}
