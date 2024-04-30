package com.javap.proiect.jpa;

import com.google.zxing.EncodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.javap.proiect.jpa.controller.citire_qr;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class ProiectApplicationTests {

    @Autowired
    private com.javap.proiect.jpa.repo.clientrepo clientrepo;
    @Autowired
    private com.javap.proiect.jpa.repo.produsrepo produsrepo;
    @Autowired
    private com.javap.proiect.jpa.repo.comandarepo comandarepo;
    public com.javap.proiect.jpa.entity.comanda comanda;
    public com.javap.proiect.jpa.controller.generare_qr generare_qr;
    public citire_qr citire_qr;
    Random rand= new Random();

    @Autowired
    private com.javap.proiect.jpa.controller.generare_email generare_email;

    @Test
    void qrtest() throws NotFoundException, IOException {

        generare_qr.generare(1111,1111);
        Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap< EncodeHintType, ErrorCorrectionLevel >();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        assertEquals("ORD:1111 PIN:1111", citire_qr.readQRCode("C:\\Users\\Ivan Fernolendt\\Desktop\\order_1111.png","UTF-8",hintMap));
    }
    @Test
    void contextLoads() {
    }

}
