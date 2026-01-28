package com.proyecto.proyecto_base.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDTO {
    private boolean success;
    private String message;
}
