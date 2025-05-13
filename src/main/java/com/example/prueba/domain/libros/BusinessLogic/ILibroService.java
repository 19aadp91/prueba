package com.example.prueba.domain.libros.BusinessLogic;

import java.util.List;

import com.example.prueba.domain.libros.dtos.CreateLibro;
import com.example.prueba.domain.libros.dtos.GetLibro;
import com.example.prueba.domain.libros.dtos.UpdateLibro;

public interface ILibroService {
    List<GetLibro> listarLibros();
    void crearLibro(CreateLibro dto);
    void actualizarLibro(UpdateLibro dto);
    void eliminarLibro(Long id);
}
