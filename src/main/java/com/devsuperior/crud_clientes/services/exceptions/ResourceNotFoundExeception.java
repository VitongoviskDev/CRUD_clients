package com.devsuperior.crud_clientes.services.exceptions;

public class ResourceNotFoundExeception extends RuntimeException{
    public ResourceNotFoundExeception(String msg){
        super(msg);
    }
}
