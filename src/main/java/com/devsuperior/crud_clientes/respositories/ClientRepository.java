package com.devsuperior.crud_clientes.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.crud_clientes.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
