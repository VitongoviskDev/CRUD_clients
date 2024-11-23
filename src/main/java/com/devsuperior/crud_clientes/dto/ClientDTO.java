package com.devsuperior.crud_clientes.dto;

import java.time.LocalDate;

import com.devsuperior.crud_clientes.entities.Client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public class ClientDTO {
    private Long id;

    @NotBlank(message = "campo requerido")
    private String name;
    private String cpf;
    private double income;
    
    @NotNull(message = "campo requerido")
    @PastOrPresent(message = "birthdate can not be a future date")
    private LocalDate birthDate;
    private Integer children;

    
    public ClientDTO() {
    }

    public ClientDTO(Long id, String name, String cpf, double income, LocalDate birthDate, Integer children) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }

    public ClientDTO(Client entity) {
        id = entity.getId();
        name = entity.getName();
        cpf = entity.getCpf();
        income = entity.getIncome();
        birthDate = entity.getBirthDate();
        children = entity.getChildren();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public double getIncome() {
        return income;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Integer getChildren() {
        return children;
    }   
}
