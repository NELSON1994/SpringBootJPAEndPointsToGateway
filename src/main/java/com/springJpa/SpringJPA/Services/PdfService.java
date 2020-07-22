package com.springJpa.SpringJPA.Services;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.springJpa.SpringJPA.Entities.User;
import com.sun.rowset.internal.Row;
import javafx.scene.control.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * @Aurthor Nelson Mose
 * @Date 2020:07:16
 */
@Service
public class PdfService {
    @Autowired
    private UploadCSVFile uploadCSVFile;
    // read data from db and write to pdf
    public int createPdf() throws DocumentException, FileNotFoundException {
        int i=0;
        final String directory="D://PDF";
        final String filename="Generated.pdf";
        Document document=new Document();
        // SET THE PATH OF DOWNLOADS
        File file=new File(directory + "/" + filename);
        //write to pdf
        PdfWriter pdfWriter=PdfWriter.getInstance(document,new FileOutputStream(file));
        // setting pdf attributes (aurthor,creation date,creator,title,subject)
        document.addAuthor("NELSON M.O");
        document.addCreationDate();
        document.addTitle("SPRING BOOT PDF");
        document.addSubject("Proud of This");
        document.addCreator("M.N.O");

        //open doc
        document.open();
        //write content to pdf
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>writting started>>>>>>>>>>>>>>>>>>>");
    Paragraph pp=new Paragraph();
    pp.setAlignment(Element.ALIGN_CENTER);
    pp.add("LIST OF USERS");
    document.add(pp);
       // document.add(new Paragraph("LIST OF USERS"));
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Done writting>>>>>>>>>>>>>>>>>>>");

        //add image to the file
        try {
            Image  image=Image.getInstance("image/background.jpg");
            image.setAbsolutePosition(100f,550f);
            //set image height and width
            image.scaleAbsolute(200,200);

            // add image to document
           //===================================== document.add(image);

       // generate tables in the pdf with 4 columns
            PdfPTable pdfPTable=new PdfPTable(4);
            pdfPTable.setWidthPercentage(100);
            pdfPTable.setSpacingBefore(10f);
            pdfPTable.setSpacingAfter(10f);
            //set column widths
            float[] columnWidths={1f,1f,1f,1f};
            pdfPTable.setWidths(columnWidths);

            List<PdfPCell> pdfPCells=new ArrayList<>();
            PdfPCell P1=new PdfPCell(new Paragraph("NAME"));
            PdfPCell P2=new PdfPCell(new Paragraph("EMAIL"));
            PdfPCell P3=new PdfPCell(new Paragraph("AGE"));
            PdfPCell P4=new PdfPCell(new Paragraph("COUNTRY CODE"));
            pdfPCells.add(P1);pdfPCells.add(P2);pdfPCells.add(P3);pdfPCells.add(P4);
            for (PdfPCell p:pdfPCells){
                p.setPaddingLeft(10f);
                p.setBorderColor(BaseColor.BLUE);
                p.setHorizontalAlignment(Element.ALIGN_CENTER);
                p.setVerticalAlignment(Element.ALIGN_MIDDLE);
                p.setBackgroundColor(BaseColor.GRAY);
            }
            pdfPTable.addCell(P1);
            pdfPTable.addCell(P2);
            pdfPTable.addCell(P3);
            pdfPTable.addCell(P4);


            // WRITE DATA TO THE CELLS
            List<User> users=uploadCSVFile.fetchAll();

            for (User user:users){
             PdfPCell p=new PdfPCell(new Paragraph(user.getName()));
                PdfPCell p1=new PdfPCell(new Paragraph(user.getEmail()));
                PdfPCell p2=new PdfPCell(new Paragraph(String.valueOf(user.getAge())));
                PdfPCell p3=new PdfPCell(new Paragraph(user.getCountryCode()));
                pdfPTable.addCell(p);
                pdfPTable.addCell(p1);
                pdfPTable.addCell(p2);
                pdfPTable.addCell(p3);

            }

            document.add(pdfPTable);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //close the document
        document.close();
        //close writer
        pdfWriter.close();

        return i;
    }



}
