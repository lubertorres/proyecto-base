package com.proyecto.proyecto_base.dao;

import com.proyecto.proyecto_base.dto.model.Estudiante;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

@Repository
public class EstudianteDao {
    private final JdbcTemplate jdbcTemplate;

    private final SimpleJdbcCall spInsertarEstudiante;

    public EstudianteDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.spInsertarEstudiante = new SimpleJdbcCall(jdbcTemplate).withProcedureName("spInsertarEstudiante");
    }

    public int guardarEstudiante(Estudiante estudiante)
    {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("e_nombre_completo", estudiante.getNombre_completo());

        spInsertarEstudiante.execute(params);
        return 1;
    }
}
