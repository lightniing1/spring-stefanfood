package com.stefanini.stefanfood.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "empresa")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotBlank(message = "o campo 'Nome' não pode estar em branco")
    private String nome;
    @NotBlank(message = "o campo 'Nome Fantasia' não pode estar em branco")
    private String nomeFantasia;
    @NotBlank(message = "o campo 'CNPJ' não pode estar em branco")
    @Column(length = 14)
    private String cnpj;
    @NotBlank(message = "o campo 'e-mail' não pode estar em branco")
    @Email
    private String email;
    @NotBlank(message = "o campo 'endereco' não pode estar em branco")
    private String endereco;
    @NotBlank(message = "o campo 'cep' nao pode estar em branco")
    private String cep;
    @NotBlank(message = "o campo 'numero' nao pode estar em branco")
    private String numeroEndereco;

    public String getNumeroEndereco() {
        return numeroEndereco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setNumeroEndereco(String numeroEndereco) {
        this.numeroEndereco = numeroEndereco;
    }
}