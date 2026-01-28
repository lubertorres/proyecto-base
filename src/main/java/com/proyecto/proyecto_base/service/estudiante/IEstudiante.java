package com.proyecto.proyecto_base.service.estudiante;

import com.proyecto.proyecto_base.dto.model.Estudiante;
import com.proyecto.proyecto_base.dto.response.ResponseDTO;

public interface IEstudiante {
    ResponseDTO guardarEstudiante(Estudiante estudiante);
}
