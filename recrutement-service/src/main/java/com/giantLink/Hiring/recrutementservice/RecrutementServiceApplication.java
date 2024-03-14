package com.giantLink.Hiring.recrutementservice;

import com.giantLink.Hiring.recrutementservice.utilities.DataBaseUtility; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RecrutementServiceApplication implements CommandLineRunner
{
	@Autowired
	DataBaseUtility databaseUtility;

	public static void main(String[] args){
		SpringApplication.run(RecrutementServiceApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception{
		databaseUtility.initDatabase();
	}

}