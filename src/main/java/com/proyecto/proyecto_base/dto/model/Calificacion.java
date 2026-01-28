package com.proyecto.proyecto_base.dto.model;

import lombok.Data;

@Data
public class Calificacion {
    private String nom_actividad;
    private Integer id_estudiante;
    private Integer id_curso;
    private Integer id_materia;
    private Float calificacion;
}
