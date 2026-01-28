package com.proyecto.proyecto_base.service;

import com.proyecto.proyecto_base.dto.model.Usuario;
import com.proyecto.proyecto_base.dto.response.ResponseDTO;

import java.util.List;

public interface IUsuario {

    ResponseDTO guardarUsuario(Usuario usuario);
    List<Usuario> obtenerUsuarios();
}
