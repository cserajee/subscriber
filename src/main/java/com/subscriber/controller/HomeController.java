package com.subscriber.controller;

import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/")
    public String Home() { 
        return readData();
    }
	
	public String readData() {
		String data;
		try {
			 FileReader file = new FileReader("src/main/resources/data.txt");
	    	  BufferedReader buffer = new BufferedReader(file);  
	    	  data = buffer.readLine();
	    	  buffer.close();  
	    	  return data;
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
		
		return "Waiting For Message...";
	}
}
