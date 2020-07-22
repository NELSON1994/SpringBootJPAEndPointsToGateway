package com.springJpa.SpringJPA.Services;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.springJpa.SpringJPA.Entities.User;
import com.springJpa.SpringJPA.Models.UsersModelCsv;
import com.springJpa.SpringJPA.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
/**
 * @Aurthor Nelson Mose
 * @Date 2020:07:16
 */
@Service
public class UploadCSVFile {
    @Autowired
    private UserRepository userRepository;

    // fetch all users
    public List<User> fetchAll(){
        List<User> u=userRepository.findAll();
        return u;
    }

    //SINGLE FILE UPLOAD
    public String uploadCsvFile(MultipartFile file) throws IOException {
        String message=null;
        //get starting time
        final  long startTime=System.currentTimeMillis();
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^READING DATE HAS STARTED^^^^^^^^^^^^^^^^^^^^^^^^^^======>" + startTime);
        // validate the file
        if (file.isEmpty()){
            message="Select a CSV file to Upload";
        }
       // else if (file.getSize())
        else{
            // passing the csv file
            Reader reader=new BufferedReader(new InputStreamReader(file.getInputStream()));

            //create csv bean reader
            CsvToBean csvCsvToBean=new CsvToBeanBuilder(reader)
                    .withType(UsersModelCsv.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            //convert csvToBean to list of users
            List<UsersModelCsv> usersModelCsvs=csvCsvToBean.parse();
            System.out.println("##########################################  "+ usersModelCsvs);
            for (UsersModelCsv u:usersModelCsvs){
                User user=new User();
                user.setEmail(u.getEmail());
                user.setAge(u.getAge());
                user.setCountryCode(u.getCountryCode());
                user.setName(u.getName());

                userRepository.save(user);
            }
            final  long EndTime=System.currentTimeMillis();
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^SAVING DONE^^^^^^^^^^^^^^^^^^^^^^^^^^=====>" +EndTime);
            long duration=EndTime-startTime;
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^DURATION TAKEN ^^^^^^^^^^^^^^^^^^^^^^^^^^=====>" +duration);
            message="úpload Successfull";
        }

        return  message;
    }



    // UPLOAD MULTIPLE FILES

    public String uploadCsvFileMultipleFiles(MultipartFile[] filee) throws IOException {
        String message=null;
        //get starting time
        final  long startTime=System.currentTimeMillis();
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^READING DATE HAS STARTED^^^^^^^^^^^^^^^^^^^^^^^^^^======>" + startTime);
        // validate the file
        for (MultipartFile file:filee){
            if (file.isEmpty()){
                message="Select a CSV file to Upload";
            }
            // else if (file.getSize())
            else{
                // passing the csv file
                Reader reader=new BufferedReader(new InputStreamReader(file.getInputStream()));

                //create csv bean reader
                CsvToBean csvCsvToBean=new CsvToBeanBuilder(reader)
                        .withType(UsersModelCsv.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();
                //convert csvToBean to list of users
                List<UsersModelCsv> usersModelCsvs=csvCsvToBean.parse();
                for (UsersModelCsv u:usersModelCsvs){
                    User user=new User();
                    user.setEmail(u.getEmail());
                    user.setAge(u.getAge());
                    user.setCountryCode(u.getCountryCode());
                    user.setName(u.getName());

                    userRepository.save(user);
                }
        }

        }
        final  long EndTime=System.currentTimeMillis();
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^SAVING DONE^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^=====>" +EndTime);
        long duration=EndTime-startTime;
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^DURATION TAKEN ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^=====>" +duration);
        message="Files úpload Successfull";

        return  message;
    }


    // providing Async in reading file

    //SINGLE FILE UPLOAD
    @Async
    public CompletableFuture<String> uploadCsvFileAsync(MultipartFile file) throws IOException {
        String message=null;
        //get starting time
        final  long startTime=System.currentTimeMillis();
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^READING DATE HAS STARTED^^^^^^^^^^^^^^^^^^^^^^^^^^======>" + startTime);
        // validate the file
        if (file.isEmpty()){
            message="Select a CSV file to Upload";
        }
        // else if (file.getSize())
        else{
            // passing the csv file
            Reader reader=new BufferedReader(new InputStreamReader(file.getInputStream()));

            //create csv bean reader
            CsvToBean csvCsvToBean=new CsvToBeanBuilder(reader)
                    .withType(UsersModelCsv.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            //convert csvToBean to list of users
            List<UsersModelCsv> usersModelCsvs=csvCsvToBean.parse();
            System.out.println("##########################################  "+ usersModelCsvs);
            for (UsersModelCsv u:usersModelCsvs){
//                List<User> usersAlreadySaved=new ArrayList<>();
//                Optional<User> user1=userRepository.findByEmail(u.getEmail());
//                if (user1.isPresent()){
//                    StringBuilder userd=null;
//                    userd.append((u.getId())
//                    ).append(",").append(u.getName()).append(",").append(u.getEmail())
//                            .append(",").append(u.getCountryCode()).append("/n");
//
//                }
               // else {
                    User user = new User();
                    user.setEmail(u.getEmail());
                    user.setAge(u.getAge());
                    user.setCountryCode(u.getCountryCode());
                    user.setName(u.getName());

                    userRepository.save(user);
               // }
            }
            final  long EndTime=System.currentTimeMillis();
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^SAVING DONE^^^^^^^^^^^^^^^^^^^^^^^^^^=====>" +EndTime);
            long duration=EndTime-startTime;
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^DURATION TAKEN ^^^^^^^^^^^^^^^^^^^^^^^^^^=====>" +duration);
            message="úpload Successfull";
        }

        return CompletableFuture.completedFuture(message);
    }
}
