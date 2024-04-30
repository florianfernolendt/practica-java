package com.javap.proiect.jpa.repo;

import com.javap.proiect.jpa.entity.client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface clientrepo extends JpaRepository <client,Integer>{
    public static client updateemail(client client, String email){
        client.email=email;
        return client;
    }
}
