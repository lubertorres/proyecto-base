package com.proyecto.proyecto_base.service.calificacion;

import com.proyecto.proyecto_base.dto.ObtenerEstudianteDTO;
import com.proyecto.proyecto_base.dto.model.Calificacion;
import com.proyecto.proyecto_base.dto.response.ResponseDTO;

public interface ICalificacion {
    ResponseDTO guardarCalificacion(Calificacion calificacion);
    ResponseDTO obtenerPromedioIndividual(ObtenerEstudianteDTO dto);
    public ResponseDTO obtenerPromedioCurso(int id_curso);

}
