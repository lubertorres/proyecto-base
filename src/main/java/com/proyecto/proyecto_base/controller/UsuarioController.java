package com.proyecto.proyecto_base.controller;

import java.util.List;

import com.proyecto.proyecto_base.dto.model.Usuario;
import com.proyecto.proyecto_base.dto.response.ResponseDTO;
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
import org.springframework.web.bind.annotation.*;

import com.proyecto.proyecto_base.service.UsuarioService;


@RestController
@Validated
@RequestMapping("/api/v1")
@Tag(name = "Api Usuarios", description = "Permite crear y obtener usuarios")
public class UsuarioController {

    private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping(value = "/usuarios", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Crear usuario", description = "Servicio para guardar un usuario en la base de datos")
    @ApiResponses(@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ResponseDTO.class))
    }))
    public ResponseEntity<ResponseDTO> guardarUsuario(@RequestBody Usuario usuario) {
        log.info("MENSAJE ENTRADA: {}", usuario);

        ResponseDTO response = usuarioService.guardarUsuario(usuario);
        log.info("MENSAJE SALIDA: {}", response);

        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping(value = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtener usuarios", description = "Servicio para listar todos los usuarios")
    @ApiResponses(@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Usuario.class))
    }))
    public ResponseEntity<List<Usuario>> obtenerUsuarios() {
        List<Usuario> usuarios = usuarioService.obtenerUsuarios();
        log.info("USUARIOS ENCONTRADOS: {}", usuarios);

        return ResponseEntity.ok(usuarios);
    }
}
