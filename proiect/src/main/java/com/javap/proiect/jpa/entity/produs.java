package com.javap.proiect.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity

public class produs {
    @Id
    public int ID;
    public String numeprod;
    public int cantitate;
    public double pret;
    //private String descriere;
    //private ArrayList sau doar List <opinii>

    public int getid(){
        return ID;
    }

    public String getnume(){
        return numeprod;
    }

    public int getcant(){
        return cantitate;
    }

    public double getpret(){
        return pret;
    }
}
