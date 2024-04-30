package com.javap.proiect.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity

public class comanda {
    @Id
    @GeneratedValue
    public int ID;
    @ManyToOne(targetEntity = client.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "comandaclient_fk", referencedColumnName = "ID")
    public client client;
    @ManyToMany(targetEntity = produs.class)
    @JoinColumn(name = "comandaprodus_fk", referencedColumnName = "ID")
    public List<produs> produse;

    public int getidclient(){
        return client.ID;
    }
    public int getidcomanda(){
        return ID;
    }
    public int getsizelist(){
        return produse.size();
    }
    public List<produs> prd(){
        return produse;
    }

    public String getemail(){
        return client.getemail();
    }

    public String getnumecl(){
        return client.getnume();
    }

    public String getadresa(){
        return client.getadresa();
    }

}


//asta nu e primary key deci nu e problema de duplicat
//vezi ca e ceva problema si nu lasa duplicate din nu stiu ce motiv
//duplicate as in in al doilea post is aceleasi produse cu acelasi id si nu ma lasa idk
//!!!in client imi face duplicate dar in produs nu
//!!!!!!!cred ca trebuie schimbata doar ordinea in care sunt scrise sau tipul de relatie
//idk sterge din baza de date si vezi in final
