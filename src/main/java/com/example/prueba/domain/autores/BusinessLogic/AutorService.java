package com.example.prueba.domain.autores.BusinessLogic;

import com.example.prueba.domain.autores.dtos.CreateAutor;
import com.example.prueba.domain.autores.dtos.GetAutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class AutorService implements  IAutorService{

    @Autowired
    private DataSource dataSource;

    public void insertarAutor(CreateAutor create) {
        try (Connection conn = dataSource.getConnection();
            CallableStatement stmt = conn.prepareCall("{ call insertar_autor(?) }")) {
            stmt.setString(1, create.nombre());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error insertando autor", e);
        }
    }

    public void actualizarAutor(GetAutor Update) {
        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall("{ call actualizar_autor(?, ?) }")) {
            stmt.setLong(1, Update.id());
            stmt.setString(2, Update.nombre());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando autor", e);
        }
    }

    public void eliminarAutor(Long id) {
        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall("{ call eliminar_autor(?) }")) {
            stmt.setLong(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando autor", e);
        }
    }

    public List<GetAutor> listarAutores() {
        List<GetAutor> autores = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall("{ call obtener_autores(?) }")) {
    
            stmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR); // Usa OracleTypes.CURSOR
            stmt.execute();
    
            try (ResultSet rs = (ResultSet) stmt.getObject(1)) {
                while (rs.next()) {
                    Long id = rs.getLong("id_autor");
                    String nombre = rs.getString("nombre");
                    autores.add(new GetAutor(id, nombre));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error consultando autores", e);
        }
        return autores;
    }
}
