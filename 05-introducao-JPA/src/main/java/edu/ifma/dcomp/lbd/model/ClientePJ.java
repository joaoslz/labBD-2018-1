package edu.ifma.dcomp.lbd.model;

import javax.persistence.Entity;

@Entity
public class ClientePJ extends Cliente {


    private String documento;


    public String getDocumento() {
        return null;
    }

    public void setDocumento(String numero) {
        this.documento = numero;
    }
}
