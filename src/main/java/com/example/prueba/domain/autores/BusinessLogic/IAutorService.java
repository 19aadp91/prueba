package com.example.prueba.domain.autores.BusinessLogic;

import java.util.List;

import com.example.prueba.domain.autores.dtos.CreateAutor;
import com.example.prueba.domain.autores.dtos.GetAutor;

public interface IAutorService {
    void insertarAutor(CreateAutor create);
    void actualizarAutor(GetAutor Update);
    void eliminarAutor(Long id);
    List<GetAutor> listarAutores();
}
