package com.proyecto.proyecto_base.dao;

import com.proyecto.proyecto_base.dto.model.Usuario;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcCall spInsertUser;
    private final SimpleJdbcCall spGetUsers;

    public UsuarioDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

        this.spInsertUser = new SimpleJdbcCall(jdbcTemplate).withProcedureName("sp_insert_user");
        this.spGetUsers   = new SimpleJdbcCall(jdbcTemplate).withProcedureName("sp_get_users");
    }

    public int guardar(Usuario usuario) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("e_username", usuario.getUsername())
                .addValue("e_password", usuario.getPassword());

        spInsertUser.execute(params);
        return 1;
    }

    public List<Usuario> listar() {
        return jdbcTemplate.query("CALL sp_get_users()", (rs, rowNum) -> {
            Usuario u = new Usuario();
            u.setUsername(rs.getString("username"));
            u.setPassword(rs.getString("password"));
            return u;
        });
    }
}
