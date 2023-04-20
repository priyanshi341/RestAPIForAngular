package com.simplilearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImageUploadAndDownloadServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImageUploadAndDownloadServiceApplication.class, args);
		System.out.println("Server Started");
	}

}
