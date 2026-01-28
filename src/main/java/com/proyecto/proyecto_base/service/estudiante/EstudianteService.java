package com.proyecto.proyecto_base.service.estudiante;

import com.proyecto.proyecto_base.dao.EstudianteDao;
import com.proyecto.proyecto_base.dto.model.Estudiante;
import com.proyecto.proyecto_base.dto.response.ResponseDTO;

import org.springframework.stereotype.Service;

@Service
public class EstudianteService implements IEstudiante {

    public EstudianteService(EstudianteDao estudianteDao) {
        this.estudianteDao = estudianteDao;
    }

    private final EstudianteDao estudianteDao;

    @Override
    public ResponseDTO guardarEstudiante(Estudiante estudiante) {
        try {
            int filas = estudianteDao.guardarEstudiante(estudiante);
            if (filas > 0) {
                return new ResponseDTO(true, "Estudiante guardado correctamente");
            } else {
                return new ResponseDTO(false, "No se pudo guardar el estudiante");
            }
        } catch (Exception e) {
            return new ResponseDTO(false, "Error: " + e.getMessage());
        }
    }
}
