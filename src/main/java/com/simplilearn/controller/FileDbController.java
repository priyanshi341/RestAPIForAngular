package com.simplilearn.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.simplilearn.entity.FileDb;
import com.simplilearn.entity.ResponseFile;
import com.simplilearn.service.FileDbService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class FileDbController {

	@Autowired
	private FileDbService service;
	
	@PostMapping("/upload")
	public FileDb uploadfile(@RequestParam("file") MultipartFile file) throws IOException
	{
		return service.store(file);
	}
	
	@GetMapping("/file")
	public List<ResponseFile> getlistfile()
	{
		List<ResponseFile> files=service.geall().map(dbfile->{
			String s=ServletUriComponentsBuilder.fromCurrentContextPath().path("/file/").path(dbfile.getId()).toUriString();
			
			return new ResponseFile(
					dbfile.getName(),s,dbfile.getType(),dbfile.getData().length);
		}).collect(Collectors.toList());
		 	
		
		return  files;
	}
	
	@GetMapping("/file/{id}")
	public byte[] getfile(@PathVariable String id)
	{
		FileDb refrence=service.getFile(id);
		return refrence.getData();
	}
	
}
