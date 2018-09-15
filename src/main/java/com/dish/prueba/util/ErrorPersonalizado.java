package com.dish.prueba.util;


public class ErrorPersonalizado {

    private String mensajeDeError;

    public ErrorPersonalizado(String mensajeDeError){
        this.mensajeDeError = mensajeDeError;
    }

    public String getMensajeDeError() {
        return mensajeDeError;
    }

}
