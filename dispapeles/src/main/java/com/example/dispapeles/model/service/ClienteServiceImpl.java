package com.example.dispapeles.model.service;

import com.example.dispapeles.model.dao.IClienteDao;
import com.example.dispapeles.model.entity.Cliente;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private IClienteDao clienteDao;

    @Override
    public List<Cliente> consultarClientes() {
        return (List<Cliente>) clienteDao.findAll();
    }

    @Override
    public Optional<Cliente> consultarXNumeroDocumento(String numeroDocumento) {
        try {
            return clienteDao.findById(numeroDocumento);
        } catch (Exception e) {
            log.error(e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<Cliente> guardarCliente(Cliente cliente) {
        try {
            return Optional.of(clienteDao.save(cliente));
        } catch (Exception e) {
            log.error(e);
            return Optional.empty();
        }
    }

    @Override
    public boolean eliminarCliente(String numeroDocumento) {
        try {
            clienteDao.deleteById(numeroDocumento);
            return Boolean.TRUE;
        } catch (Exception e) {
            log.error(e);
            return Boolean.FALSE;
        }
    }
}
