package com.example.dispapeles.controller;

import antlr.StringUtils;
import ch.qos.logback.core.net.server.Client;
import com.example.dispapeles.model.entity.Cliente;
import com.example.dispapeles.model.service.IClienteService;
import com.example.dispapeles.view.ClienteDto;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    public ResponseEntity consultarCliente(String numeroIdentificacion) {
        ClienteDto clienteDto = new ClienteDto();

        Optional<Cliente> optionalCliente = clienteService.consultarXNumeroDocumento(numeroIdentificacion);
        if(!optionalCliente.isPresent()) {
            return new ResponseEntity<>("No se encontro ningún cliente con numero de documento: ".concat(numeroIdentificacion), HttpStatus.NOT_FOUND);
        }

        clienteService.consultarXNumeroDocumento(numeroIdentificacion)
                .ifPresent(cliente -> {
                    clienteDto.setApellidos(cliente.getApellidos());
                    clienteDto.setEdad(cliente.getEdad());
                    clienteDto.setNombre(cliente.getNombre());
                    clienteDto.setDireccion(cliente.getDireccion());
                    clienteDto.setTelefono(cliente.getTelefono());
                    clienteDto.setNumeroIdentificacion(cliente.getNumeroIdentificacion());
                    clienteDto.setTipoIdentificacion(cliente.getTipoIdentificacion());
                });

        return new ResponseEntity<>(clienteDto, HttpStatus.OK);
    }

    public List<ClienteDto> consultarClientes() {
        List<ClienteDto> clienteDtoList = new ArrayList<>();
        List<Cliente> clienteList = clienteService.consultarClientes();
        ClienteDto clienteDto = new ClienteDto();

        for (Cliente item : clienteList) {
            clienteDto.setTelefono(item.getTelefono());
            clienteDto.setApellidos(item.getApellidos());
            clienteDto.setEdad(item.getEdad());
            clienteDto.setNumeroIdentificacion(item.getNumeroIdentificacion());
            clienteDto.setTipoIdentificacion(item.getTipoIdentificacion());
            clienteDto.setDireccion(item.getDireccion());
            clienteDto.setNombre(item.getNombre());
            clienteDtoList.add(clienteDto);
            clienteDto = new ClienteDto();
        }

        return clienteDtoList;
    }

    public ResponseEntity guardarCliente(ClienteDto clienteDto) {

        String mensajeValidacion = validarParametros(clienteDto);

        if (Strings.isNotEmpty(mensajeValidacion)) {
            return new ResponseEntity<String>(mensajeValidacion, HttpStatus.BAD_REQUEST);
        }

        if (clienteService.guardarCliente(
                        Cliente.builder()
                                .apellidos(clienteDto.getApellidos())
                                .edad(clienteDto.getEdad())
                                .nombre(clienteDto.getNombre())
                                .direccion(clienteDto.getDireccion())
                                .telefono(clienteDto.getTelefono())
                                .numeroIdentificacion(clienteDto.getNumeroIdentificacion())
                                .tipoIdentificacion(clienteDto.getTipoIdentificacion())
                                .build()
                )
                .isPresent()) {
            return new ResponseEntity<>("El cliente se guardo correctamente", HttpStatus.OK);
        }

        return new ResponseEntity<>("Error al guardar el cliente", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String validarParametros(ClienteDto clienteDto) {
        StringBuilder mensajeValidacion = new StringBuilder();

        if (Objects.isNull(clienteDto)) {
            mensajeValidacion.append("Debe ingresar la información del cliente, ");
        }

        if (Strings.isBlank(clienteDto.getNumeroIdentificacion()) || Strings.isEmpty(clienteDto.getNumeroIdentificacion())) {
            mensajeValidacion.append("El numero de identificación es obligatorio,  ");
        }

        if (Strings.isBlank(clienteDto.getTipoIdentificacion()) || Strings.isEmpty(clienteDto.getTipoIdentificacion())) {
            mensajeValidacion.append("El tipo de identificación es obligatorio, ");
        }

        if (Strings.isBlank(clienteDto.getNombre()) || Strings.isEmpty(clienteDto.getNombre())) {
            mensajeValidacion.append("El nombre es obligatorio, ");
        }

        if (Strings.isBlank(clienteDto.getApellidos()) || Strings.isEmpty(clienteDto.getApellidos())) {
            mensajeValidacion.append("Los apellidos son obligatorio, ");
        }

        if (Strings.isBlank(clienteDto.getDireccion()) || Strings.isEmpty(clienteDto.getDireccion())) {
            mensajeValidacion.append("La dirección es obligatoria, ");
        }

        if (Objects.isNull(clienteDto.getEdad())) {
            mensajeValidacion.append("La edad es obligatoria, ");
        }

        if (Strings.isBlank(clienteDto.getTelefono()) || Strings.isEmpty(clienteDto.getTelefono())) {
            mensajeValidacion.append("El telefono es obligatorio, ");
        }

        return mensajeValidacion.toString();
    }


    public ResponseEntity eliminarCliente(String numeroIdentificacion) {
        if (clienteService.eliminarCliente(numeroIdentificacion)) {
            return new ResponseEntity<>("El cliente se elimino correctamente", HttpStatus.OK);
        }

        return new ResponseEntity<>("Error al elmininar el cliente", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
