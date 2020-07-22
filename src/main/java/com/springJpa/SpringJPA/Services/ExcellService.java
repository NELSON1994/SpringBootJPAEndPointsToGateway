package com.springJpa.SpringJPA.Services;

import com.springJpa.SpringJPA.Entities.User;
import com.springJpa.SpringJPA.Repositories.UserRepository;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 * @Aurthor Nelson Mose
 * @Date 2020:07:16
 */
@Service
public class ExcellService {
    @Autowired
    private UserRepository userRepository;

    //WRITING TO  EXCELL FILE
    public String writeToExcellFile() throws IOException {
        String response=null;
        XSSFWorkbook xssfWorkbook=new XSSFWorkbook();
        XSSFSheet xssfSheet=xssfWorkbook.createSheet("GeneratedExcellFile");

        XSSFRow xssfRow=xssfSheet.createRow(1);
        XSSFCell xssfCell;
        xssfCell=xssfRow.createCell(1);
        xssfCell.setCellValue("NO.");
        xssfCell=xssfRow.createCell(2);
        xssfCell.setCellValue("NAME");
        xssfCell=xssfRow.createCell(3);
        xssfCell.setCellValue("EMAIL");
        xssfCell=xssfRow.createCell(4);
        xssfCell.setCellValue("AGE");
        xssfCell=xssfRow.createCell(5);
        xssfCell.setCellValue("COUNTRY CODE");

        int i=2;
        //FETCH LIST OF USERS
        List<User> users=userRepository.findAll();
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&========USERS length+++++++++++++++" +users.size());
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&========USERS+++++++++++++++" +users);
        for (User user:users){
           xssfRow=xssfSheet.createRow(i);
            xssfCell=xssfRow.createCell(1);
            xssfCell.setCellValue(String.valueOf(i-1));
            xssfCell=xssfRow.createCell(2);
            xssfCell.setCellValue(user.getName());
            xssfCell=xssfRow.createCell(3);
            xssfCell.setCellValue(user.getEmail());
            xssfCell=xssfRow.createCell(4);
            xssfCell.setCellValue(String.valueOf(user.getAge()));
            xssfCell=xssfRow.createCell(5);
            xssfCell.setCellValue(user.getCountryCode());
            i++;
        }
        FileOutputStream fileOutputStream=new FileOutputStream(new File("D://PDF/excel.xlsx"));
        xssfWorkbook.write(fileOutputStream);
        fileOutputStream.close();
        response="Succesfull writing to the excell file";
        return response;
    }

    public String readFromExcell(MultipartFile file) throws IOException, InvalidFormatException {
        File file1=new File(file.getName());
       FileInputStream fileInputStream=new FileInputStream(file1);
        Workbook workbook= new XSSFWorkbook(fileInputStream);
       // getting number of sheets
        int no=workbook.getNumberOfSheets();
        System.out.println("+++++++++++++++++++++++/////+No of sheets============="+no);
        // iterate over the sheets
        Iterator<Sheet> sheetIterator=workbook.sheetIterator();
        System.out.println("=================Iterating over the sheet with iterator()============");
        while ((sheetIterator.hasNext())) {
            Sheet sheet=sheetIterator.next();
            System.out.println("===================SHEET NAME=============="+sheet.getSheetName());

            Sheet sheet1=workbook.getSheetAt(0);
            Iterator<Row> iterator=sheet1.rowIterator();
            while(iterator.hasNext()){
                Row row=iterator.next();
                Iterator<Cell> cellIterator=row.cellIterator();
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@===No of cells===@@@@@@" +row.getRowNum());
//                while (cellIterator.hasNext()){
//                    Cell cell=cellIterator.next();
//                    //switch on each cell content type
//                switch (cell.getCellType()){
//                    case BOOLEAN:
//                        break;
//                }
//                }

                double a= row.getCell(0).getNumericCellValue();
                String name= row.getCell(1).getStringCellValue();
                String email= row.getCell(2).getStringCellValue();
                int age= Integer.parseInt(row.getCell(3).getStringCellValue());
                String countrycode= row.getCell(4).getStringCellValue();

                User user=new User();
                user.setName(name);
                user.setAge(age);
                user.setEmail(email);
                user.setCountryCode(countrycode);
                userRepository.save(user);
            }
            System.out.println("==========================SAVING DONE========================================");
        }
        return "file read succesfully";
    }
}
