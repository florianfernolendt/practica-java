package com.javap.proiect.jpa.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.javap.proiect.jpa.entity.produs;

import java.io.FileOutputStream;
import java.util.List;

//https://www.youtube.com/watch?v=Zg7lS5sPN0M
//aici e tot ce sa folosit tot de aici iei si tabelul
public class generare_factura {
    public static void generare(String ordid, String text, int nr, List<produs> prod) {
        try {
            String path = "C:\\Users\\Ivan Fernolendt\\Desktop\\" + ordid + ".pdf";
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();
            //adauga imagine
            document.add(Image.getInstance("C:\\Users\\Ivan Fernolendt\\Desktop\\poza_logo.png"));

            Paragraph para = new Paragraph("FACTURA FISCALA");
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            //adauga paragraf
            para = new Paragraph(text);
            document.add(para);

            //adaugam produse
            double total = 0;
            PdfPTable table = new PdfPTable(4);
            PdfPCell coloana =new PdfPCell(new Phrase("ID PRODUS"));
            table.addCell(coloana);
            coloana =new PdfPCell(new Phrase("NUME PRODUS"));
            table.addCell(coloana);
            coloana =new PdfPCell(new Phrase("CANTIATE"));
            table.addCell(coloana);
            coloana =new PdfPCell(new Phrase("PRET"));
            table.addCell(coloana);
            table.setHeaderRows(1);
            String buffer;
            for (int i=0;i<prod.size();i++)
            {
                produs p = prod.get(i);
                buffer = String.valueOf(p.getid());
                table.addCell(buffer);
                buffer = String.valueOf(p.getnume());
                table.addCell(buffer);
                buffer = String.valueOf(p.getcant());
                table.addCell(buffer);
                buffer = String.valueOf(p.getcant()*p.getpret());
                total = total + p.getcant()*p.getpret();
                table.addCell(buffer);
            }
            document.add(table);
            buffer = String.valueOf(total);
            para = new Paragraph("TOTAL:" + buffer + "lei FARA TVA");
            para.setAlignment(Element.ALIGN_RIGHT);
            total = total + (0.19 * total);
            document.add(para);
            buffer = String.valueOf(total);
            para = new Paragraph("TOTAL:" + buffer + "lei CU TVA");
            para.setAlignment(Element.ALIGN_RIGHT);
            document.add(para);


            document.close();
        } catch (Exception e) {
            System.out.println("Eroare!");
        }

    }
}
