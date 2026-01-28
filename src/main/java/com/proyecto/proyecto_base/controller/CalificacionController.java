package com.proyecto.proyecto_base.controller;

import com.proyecto.proyecto_base.dto.ObtenerEstudianteDTO;
import com.proyecto.proyecto_base.dto.model.Calificacion;
import com.proyecto.proyecto_base.dto.response.ResponseDTO;
import com.proyecto.proyecto_base.service.calificacion.CalificacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/api/v1")
@Tag(name = "Api Calificaciones", description = "Permite crear calificaciones")
public class CalificacionController  {

    private static final Logger log = LoggerFactory.getLogger(CalificacionController.class);

    public CalificacionController(CalificacionService calificacionService) {
        this.calificacionService = calificacionService;
    }

    public final CalificacionService calificacionService;

    @PostMapping(value = "/calificaciones", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Crear calificacion", description = "Servicio para guardar una calificacion en la base de datos")
    @ApiResponses(@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ResponseDTO.class))
    }))
    public ResponseEntity<ResponseDTO> insertarCalificacion(@RequestBody Calificacion calificacion) {
        log.info("MENSAJE ENTRADA: {}", calificacion);

        ResponseDTO response = calificacionService.guardarCalificacion(calificacion);
        log.info("MENSAJE SALIDA: {}", response);

        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping(value = "/calificaciones/promedioEstudiante", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Promedio Individual", description = "Servicio para guardar una calificacion en la base de datos")
    @ApiResponses(@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ResponseDTO.class))
    }))
    public ResponseEntity<ResponseDTO> obtenerPromedioIndividual(@RequestBody ObtenerEstudianteDTO obtenerEstudianteDTO) {
        log.info("MENSAJE ENTRADA: {}", obtenerEstudianteDTO);

        ResponseDTO response = calificacionService.obtenerPromedioIndividual(obtenerEstudianteDTO);
        log.info("MENSAJE SALIDA: {}", response);

        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping(value = "/calificaciones/promedioCurso", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Promedio Curso", description = "Servicio promedio curso")
    @ApiResponses(@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ResponseDTO.class))
    }))
    public ResponseEntity<ResponseDTO> promedioCurso(@RequestBody int id_curso) {
        log.info("MENSAJE ENTRADA: {}", id_curso);

        ResponseDTO response = calificacionService.obtenerPromedioCurso(id_curso);
        log.info("MENSAJE SALIDA: {}", response);

        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }
}
