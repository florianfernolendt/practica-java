package com.javap.proiect.jpa.controller;

import com.javap.proiect.jpa.dto.raspuns;
import com.javap.proiect.jpa.dto.request;
import com.javap.proiect.jpa.entity.client;
import com.javap.proiect.jpa.entity.comanda;
import com.javap.proiect.jpa.entity.produs;
import com.javap.proiect.jpa.repo.clientrepo;
import com.javap.proiect.jpa.repo.comandarepo;
import com.javap.proiect.jpa.repo.produsrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
public class controller {
    @Autowired
    private clientrepo clientrepo;
    @Autowired
    private produsrepo produsrepo;
    @Autowired
    private comandarepo comandarepo;
    public comanda comanda;
    public generare_qr generare_qr;
    Random rand= new Random();

    @Autowired
    private generare_email generare_email;
    @PostMapping("/plasarecomanda")//create:creaza o comanda noua
    public comanda plasarecomanda(@RequestBody request re) throws MessagingException {
        comanda = comandarepo.save(re.getComanda());

        //GENERARE COD QR
        generare_qr.generare(comanda.getidcomanda(),rand.nextInt(1000,9999));
        String idcom= String.valueOf(comanda.getidcomanda());

       //GENERARE FACTURA
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        generare_factura.generare(idcom,"Comanda numarul" + idcom +"\nS.C Vanzator S.R.L \n C.U.I: RO11111111 \n Adresa: str. Strazilor, nr.14, Bucuresti\n"+ date + "\nNume client: " + comanda.getnumecl() + "\n Adresa: "+ comanda.getadresa() + "\n\n",comanda.getsizelist(),comanda.prd());

        System.out.println("Clientul cu ID-ul " + comanda.getidclient()+ "a facut o comanda. Nr comanda:" + idcom);
        //TRIMITE FACTURA
        generare_email.trimite(comanda.getemail(),"Va multumim pentru incredere! Atasat aveti factura aferenta comenzii.", "Factura comanda " + idcom,"C:/Users/Ivan Fernolendt/Desktop/" + idcom + ".pdf" );
        //TRIMITERE COD QR
        generare_email.trimite(comanda.getemail(),"Va multumim pentru incredere! Atasat aveti cod-ul QR pentru curier.", "Livrare comanda " + idcom,"C:/Users/Ivan Fernolendt/Desktop/order_" + idcom + ".png" );

        return comandarepo.save(re.getComanda());
    }
    @GetMapping("/toateprodusele")//read:afiseaaza toate produsele
    public List<produs> toateprodusele(){
        return produsrepo.findAll();
    }

    @GetMapping("/toatecomenzile")//read:afiseaza toate comenzile
    public List<comanda> toatecomezile(){
        return comandarepo.findAll();
    }

    @GetMapping("/info")//read:afiseaza numele clientului si produsele
    public List<raspuns>getJoinInformation(){
        return comandarepo.getJoinInformation();
    }

    @PutMapping("/schimbareemail/{id}")//update:schimba emailul
    public client updateemail(@RequestBody client client, @PathVariable("id") String email)
    {
        return com.javap.proiect.jpa.repo.clientrepo.updateemail(client,email);
    }

    @DeleteMapping("/stergeclient/{id}")//delete:sterge clientul
    public String stergeclient(@PathVariable("id") int ID){
        clientrepo.deleteById(ID);
        return "Sters cu succes";
    }
    //@EventListener(ApplicationReadyEvent.class)
    //ublic void triggeremail() throws MessagingException {
      //  generare_email.trimite("ifmflorian@gmail.com","acesta e un test", "test");
    //}
}

/*
JSON PENTRU TEST

{
    "comanda":{
    "client":{
        "nume":"test3",
        "email":"test@gmail.com",
        "adresa":"o adresa oarecare"
    },
        "produse":[
            {
                "ID":234,
                "numeprod":"telefon",
                "cantitate":50,
                "pret":1200
            },
             {
                "ID":267,
                "numeprod":"efon",
                "cantitate":50,
                "pret":1200
            },
            {
                "ID":203,
                "numeprod":"on",
                "cantitate":10,
                "pret":100
            }
        ]
    }
}
 */
