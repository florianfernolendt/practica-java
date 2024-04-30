package com.javap.proiect.jpa.repo;

import com.javap.proiect.jpa.dto.raspuns;
import com.javap.proiect.jpa.entity.comanda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface comandarepo extends JpaRepository<comanda,Integer > {
    @Query("SELECT new com.javap.proiect.jpa.dto.raspuns(c.client.nume , p.numeprod) FROM comanda c JOIN c.produse p")
    public List<raspuns> getJoinInformation();

}
