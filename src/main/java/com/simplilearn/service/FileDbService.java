package com.simplilearn.service;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.simplilearn.entity.FileDb;
import com.simplilearn.repository.FileDbRepository;

@Service
public class FileDbService {

	@Autowired
	public FileDbRepository ref;
	
	public FileDb store(MultipartFile file) throws IOException
	{
		String filename=StringUtils.cleanPath(file.getOriginalFilename());
		FileDb filedb=new FileDb(filename,file.getContentType(),file.getBytes());
		return ref.save(filedb);
	}
	
	public FileDb getFile(String id)
	{
		return ref.findById(id).get();
	}
	
	public Stream<FileDb> geall()
	{
		return ref.findAll().stream();
	}
}
