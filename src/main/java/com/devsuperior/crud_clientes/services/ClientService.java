package com.devsuperior.crud_clientes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.crud_clientes.dto.ClientDTO;
import com.devsuperior.crud_clientes.entities.Client;
import com.devsuperior.crud_clientes.respositories.ClientRepository;
import com.devsuperior.crud_clientes.services.exceptions.DatabaseException;
import com.devsuperior.crud_clientes.services.exceptions.ResourceNotFoundExeception;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientService {
    
    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
        Client result = clientRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundExeception("recurso não encontrado"));
        return new ClientDTO(result);
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable){
        Page<Client> results = clientRepository.findAll(pageable);
        return results.map(x -> new ClientDTO(x));
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto){
        Client entity = copyFromDto(dto);
        entity = clientRepository.save(entity);

        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto){
        try{
            Client entity = clientRepository.getReferenceById(id);
            entity = copyFromDto(dto, entity);
            entity = clientRepository.save(entity);
            return new ClientDTO(entity);
        }catch(EntityNotFoundException ex){
            throw new ResourceNotFoundExeception("Recurso não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if(!clientRepository.existsById(id)){
            throw new ResourceNotFoundExeception("Recuros não encontrado");
        }
        try{
            clientRepository.deleteById(id); 
        } catch (DataIntegrityViolationException ex) {
            throw new DatabaseException("falha de integridade referencial");
        }
    }
    //CUSTOM METHODS
    /**
     * Receive: Product entity and a ProductDTO
     * Return: received entity instance with dto's values
    */
    private Client copyFromDto(ClientDTO dto, Client entity){
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
        
        return entity;
    }

    /**
     * Receive: ProductDTO
     * Return: new Product instance with dto's values
    */
    private Client copyFromDto(ClientDTO dto){

        Client entity = new Client();

        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());

        return entity;
    }
}
