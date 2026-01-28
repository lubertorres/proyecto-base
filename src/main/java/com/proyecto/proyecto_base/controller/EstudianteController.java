package com.proyecto.proyecto_base.controller;

import com.proyecto.proyecto_base.dto.model.Estudiante;
import com.proyecto.proyecto_base.dto.response.ResponseDTO;
import com.proyecto.proyecto_base.service.estudiante.EstudianteService;
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
@Tag(name = "Api Estudiantes", description = "Permite crear estudiantes")
public class EstudianteController {
    private static final Logger log = LoggerFactory.getLogger(EstudianteController.class);

    private final EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @PostMapping(value = "/estudiantes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Crear estudiante", description = "Servicio para guardar estudiantes en la base de datos")
    @ApiResponses(@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ResponseDTO.class))
    }))
    public ResponseEntity<ResponseDTO> insertarEstudiante(@RequestBody Estudiante estudiante) {
        log.info("MENSAJE ENTRADA: {}", estudiante);

        ResponseDTO response = estudianteService.guardarEstudiante(estudiante);
        log.info("MENSAJE SALIDA: {}", response);

        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

}
