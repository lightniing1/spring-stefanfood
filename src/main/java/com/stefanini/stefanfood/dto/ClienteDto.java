package com.stefanini.stefanfood.dto;

public class ClienteDto {

    private String nomeSocial;
    private String email;

    public ClienteDto() {

    }

    public String getNomeSocial() {
        return nomeSocial;
    }

    public void setNomeSocial(String nomeSocial) {
        this.nomeSocial = nomeSocial;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
