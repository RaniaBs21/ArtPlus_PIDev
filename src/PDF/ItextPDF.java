/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PDF;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 *
 * @author akrem
 */public class ItextPDF{
public void ItextPDF(){
       try {
        Document doc = new Document(PageSize.A4);
        PdfWriter.getInstance(doc, new FileOutputStream("report.pdf") );
        doc.open();
        doc.add(new Paragraph("Reclamation reussi"));
        doc.close();
       }catch(Exception e){
           System.err.println(e);
    }
    
    }
}