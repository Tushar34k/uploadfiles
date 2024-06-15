package com.upload.demo.datacontroller;



import com.upload.demo.datarepository.UploadDataRepo;
import com.upload.demo.entity.UploadData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/file")
@RestController
public class UploadDataController {

    
	@Autowired
   private UploadDataRepo uploadDataRepo;

	@PostMapping("/upload")
	public ResponseEntity<List<String>> uploadFile(@RequestParam("file") MultipartFile file) {
	    List<String> lines = new ArrayList<>();
	    try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
	        String line;
	        while ((line = br.readLine()) != null) {
	            lines.add(line);

	            
	            UploadData uploadData = new UploadData();
	           
	            uploadData.setContent(line.substring(0, Math.min(line.length(), 255))); 
	            uploadDataRepo.save(uploadData);
	        }
	        
	    } catch (IOException e) {
	        e.printStackTrace();
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<>(lines, HttpStatus.OK);
	}

}

