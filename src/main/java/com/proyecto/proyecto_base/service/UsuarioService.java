package com.proyecto.proyecto_base.service;

import com.proyecto.proyecto_base.dao.UsuarioDao;
import com.proyecto.proyecto_base.dto.model.Usuario;
import com.proyecto.proyecto_base.dto.response.ResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IUsuario {

    private final UsuarioDao usuarioDao;

    public UsuarioService(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    @Override
    public ResponseDTO guardarUsuario(Usuario usuario) {
        try {
            int filas = usuarioDao.guardar(usuario);
            if (filas > 0) {
                return new ResponseDTO(true, "Usuario guardado correctamente");
            } else {
                return new ResponseDTO(false, "No se pudo guardar el usuario");
            }
        } catch (Exception e) {
            return new ResponseDTO(false, "Error: " + e.getMessage());
        }
    }

    @Override
    public List<Usuario> obtenerUsuarios() {
        return usuarioDao.listar();
    }
}
