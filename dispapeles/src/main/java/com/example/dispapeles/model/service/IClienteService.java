package com.example.dispapeles.model.service;

import com.example.dispapeles.model.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteService {

    List<Cliente> consultarClientes();

    Optional<Cliente> consultarXNumeroDocumento(String numeroDocumento);

    Optional<Cliente> guardarCliente(Cliente cliente);

    boolean eliminarCliente(String numeroDocumento);
}
