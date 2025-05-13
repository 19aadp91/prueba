package com.example.prueba.domain.libros.dtos;

public record UpdateLibro(
    Long id,
    String titulo,
    Long autorId
) {}
