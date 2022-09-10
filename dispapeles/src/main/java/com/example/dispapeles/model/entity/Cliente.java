package com.example.dispapeles.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cliente")
@Setter
@Getter
public class Cliente implements Serializable {

    @Id
    @Column(name = "numeroidentificacion", length = 10)
    private String numeroIdentificacion;

    @Column(name = "tipoidentificacion", length = 2)
    private String tipoIdentificacion;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "apellidos", length = 50)
    private String apellidos;

    @Column(name = "edad")
    private Integer edad;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "direccion")
    private String direccion;
}
