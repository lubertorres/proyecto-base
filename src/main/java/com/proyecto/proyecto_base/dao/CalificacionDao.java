package com.proyecto.proyecto_base.dao;

import com.proyecto.proyecto_base.dto.ObtenerEstudianteDTO;
import com.proyecto.proyecto_base.dto.model.Calificacion;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Objects;

@Repository
public class CalificacionDao {

    private final JdbcTemplate jdbcTemplate;

    private final SimpleJdbcCall spInsertarCalificacion;
    private final SimpleJdbcCall spCalcularPromedioIndividual;

    public CalificacionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.spInsertarCalificacion = new SimpleJdbcCall(jdbcTemplate).withProcedureName("spInsertarCalificacion");
        this.spCalcularPromedioIndividual = new SimpleJdbcCall(jdbcTemplate).withProcedureName("spCalcularPromedioIndividual");

    }

    public int guardarCalificacion(Calificacion calificacion)
    {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("e_nom_actividad", calificacion.getNom_actividad())
                .addValue("e_id_estudiante", calificacion.getId_estudiante())
                .addValue("e_id_curso", calificacion.getId_curso())
                .addValue("e_id_materia", calificacion.getId_materia())
                .addValue("e_calificacion", calificacion.getCalificacion());

        spInsertarCalificacion.execute(params);
        return 1;
    }

    public Double obtenerPromedioIndividual(ObtenerEstudianteDTO dto)
    {

        return jdbcTemplate.queryForObject(
                "CALL spCalcularPromedioIndividual(?, ?)",
                Double.class,
                dto.getIdEstudiante(),
                dto.getIdCurso()
        );
    }

    public Double obtenerPromedioCurso(int id_curso)
    {

        return jdbcTemplate.queryForObject(
                "CALL spCalcularPromedioCurso(?)",
                Double.class,
                id_curso
        );
    }
}
