package com.proyecto.proyecto_base.controller;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HolaMundoController {


    private final JdbcTemplate jdbcTemplate;

    public HolaMundoController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/test-db")
    public String testDB() {
        Integer result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
        return "Conexión OK ✅ -> " + result;
    }

}