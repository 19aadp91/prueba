package com.example.prueba.domain.libros.BusinessLogic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;

import com.example.prueba.domain.libros.dtos.CreateLibro;
import com.example.prueba.domain.libros.dtos.GetLibro;
import com.example.prueba.domain.libros.dtos.UpdateLibro;

@Service
public class LibroService implements ILibroService {

    @Autowired
    private DataSource dataSource;

    public List<GetLibro> listarLibros() {
        List<GetLibro> libros = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall("{ call obtener_libros(?) }")) {

            stmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            stmt.execute();

            try (ResultSet rs = (ResultSet) stmt.getObject(1)) {
                while (rs.next()) {
                    Long id = rs.getLong("ID");
                    String titulo = rs.getString("TITULO");
                    Long autorId = rs.getLong("AUTOR_ID");
                    String autorNombre = rs.getString("AUTOR_NOMBRE");
                    libros.add(new GetLibro(id, titulo, autorId, autorNombre));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error listando libros", e);
        }
        return libros;
    }

    public void crearLibro(CreateLibro dto) {
        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall("{ call insertar_libro(?, ?) }")) {
            stmt.setString(1, dto.titulo());
            stmt.setLong(2, dto.autorId());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error creando libro", e);
        }
    }

    public void actualizarLibro(UpdateLibro dto) {
        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall("{ call actualizar_libro(?, ?, ?) }")) {
            stmt.setLong(1, dto.id());
            stmt.setString(2, dto.titulo());
            stmt.setLong(3, dto.autorId());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando libro", e);
        }
    }

    public void eliminarLibro(Long id) {
        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall("{ call eliminar_libro(?) }")) {
            stmt.setLong(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando libro", e);
        }
    }
}
