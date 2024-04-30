package com.javap.proiect.jpa.dto;

import com.javap.proiect.jpa.entity.client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class request {
    public com.javap.proiect.jpa.entity.comanda comanda;

    public int getIDclient(){
        return comanda.client.ID;
    }
}
