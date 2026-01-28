package com.proyecto.proyecto_base.service.calificacion;

import com.proyecto.proyecto_base.dao.CalificacionDao;
import com.proyecto.proyecto_base.dto.ObtenerEstudianteDTO;
import com.proyecto.proyecto_base.dto.model.Calificacion;
import com.proyecto.proyecto_base.dto.response.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CalificacionService implements ICalificacion {

    private static final Logger log = LoggerFactory.getLogger(CalificacionService.class);

    public CalificacionService(CalificacionDao calificacionDao) {
        this.calificacionDao = calificacionDao;
    }

    private final CalificacionDao calificacionDao;

    @Override
    public ResponseDTO guardarCalificacion(Calificacion calificacion) {
        try {
            int filas = calificacionDao.guardarCalificacion(calificacion);
            if (filas > 0) {
                return new ResponseDTO(true, "Calificacion guardada correctamente");
            } else {
                return new ResponseDTO(false, "No se pudo guardar la calificacion");
            }
        } catch (Exception e) {
            return new ResponseDTO(false, "Error: " + e.getMessage());
        }
    }

    @Override
    public ResponseDTO obtenerPromedioIndividual(ObtenerEstudianteDTO dto)
    {
        try
        {
            Double promedio = calificacionDao.obtenerPromedioIndividual(dto);

            if (promedio == null)
            {
                return new ResponseDTO(false, "No se pudo obtener el promedio para el estudiante");
            }

            return new ResponseDTO(true, "Promnedio del estudiante es: " + promedio);
        } catch (Exception e) {
            return new ResponseDTO(false, "Error en obtener promedio individual: " + e.getMessage());
        }
    }

    @Override
    public ResponseDTO obtenerPromedioCurso(int id_curso)
    {
        try
        {
            Double promedio = calificacionDao.obtenerPromedioCurso(id_curso);

            if (promedio == null)
            {
                return new ResponseDTO(false, "No se pudo obtener el promedio para el curso");
            }

            return new ResponseDTO(true, "Promnedio del curso es: " + promedio);
        } catch (Exception e) {
            return new ResponseDTO(false, "Error en obtener promedio curso: " + e.getMessage());
        }
    }

}
