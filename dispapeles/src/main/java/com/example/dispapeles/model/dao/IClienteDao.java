package com.example.dispapeles.model.dao;

import com.example.dispapeles.model.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface IClienteDao extends CrudRepository<Cliente, String> {
}
