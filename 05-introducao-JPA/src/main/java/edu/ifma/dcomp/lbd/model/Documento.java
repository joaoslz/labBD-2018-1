package edu.ifma.dcomp.lbd.model;

import javax.persistence.Entity;

@Entity
public interface Documento {

    String getNumero();
    void setNumero(String numero);
}
