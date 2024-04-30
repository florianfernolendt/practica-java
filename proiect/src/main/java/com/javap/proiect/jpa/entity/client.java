package com.javap.proiect.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity

public class client  {
    @Id
    @GeneratedValue
    public int ID;
    private String nume;
    public String email;
    private String adresa;

    public String getemail(){
        return email;
    }

    public String getnume(){
        return nume;
    }

    public String getadresa(){
        return adresa;
    }

}
