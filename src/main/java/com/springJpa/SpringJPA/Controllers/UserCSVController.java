package com.springJpa.SpringJPA.Controllers;

import com.itextpdf.text.DocumentException;
import com.springJpa.SpringJPA.Services.ExcellService;
import com.springJpa.SpringJPA.Services.PdfService;
import com.springJpa.SpringJPA.Services.UploadCSVFile;
import com.sun.xml.internal.ws.util.CompletedFuture;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
/**
 * @Aurthor Nelson Mose
 * @Date 2020:07:16
 */
@RestController
public class UserCSVController {
    @Autowired
    private UploadCSVFile uploadCSVFile;
    @Autowired
    private PdfService pdfService;
    @Autowired
    private ExcellService excellService;

    @PostMapping("/uploadCSV")// SINGLE FILE UPLOAD
    public ResponseEntity<String> uploadCSV(@RequestBody MultipartFile file) throws IOException {
        String message=null;
       message=uploadCSVFile.uploadCsvFile(file) ;
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    // MULTIPLE FILES UPLOAD
    @PostMapping("/uploadCSVMultiple")
    public ResponseEntity<String> uploadCSVMultiple(@RequestBody MultipartFile[] file) throws IOException {
        String message=null;
        message=uploadCSVFile.uploadCsvFileMultipleFiles(file);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @PostMapping("/uploadCSVAsync")// SINGLE FILE UPLOAD with Async
    public ResponseEntity<String> uploadCSVAsync(@RequestBody MultipartFile file) throws IOException {
        CompletableFuture<String> message=null;
        message=uploadCSVFile.uploadCsvFileAsync(file);
        return ResponseEntity.status(HttpStatus.OK).body(message.toString());
    }

    @GetMapping("/createPdf")
    public ResponseEntity<Integer> createPdf() throws FileNotFoundException, DocumentException {
        int value;
        value=pdfService.createPdf();
        return ResponseEntity.status(HttpStatus.OK).body(value);
    }
    @GetMapping("/createExcell")
    public ResponseEntity<String> readToExcell() throws IOException {
        String res=null;
        res=excellService.writeToExcellFile();
        res="Succesfully written";
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
    @PostMapping("/readFromExcellToDb")
    public ResponseEntity<String> saveFromExcellToDatabase(@RequestBody MultipartFile file) throws IOException, InvalidFormatException {
        String res=excellService.readFromExcell(file);
        return ResponseEntity.status(HttpStatus.OK).body("Saved succesfull");
    }
}
