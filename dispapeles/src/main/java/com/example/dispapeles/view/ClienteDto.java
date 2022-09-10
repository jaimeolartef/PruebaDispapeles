package com.example.dispapeles.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class ClienteDto {

    private String numeroIdentificacion;
    private String tipoIdentificacion;
    private String nombre;
    private String apellidos;
    private Integer edad;
    private String telefono;
    private String direccion;
}
