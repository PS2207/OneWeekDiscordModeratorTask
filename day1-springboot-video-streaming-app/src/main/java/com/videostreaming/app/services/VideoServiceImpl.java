package com.videostreaming.app.services;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.videostreaming.app.entities.Video;
import com.videostreaming.app.repositories.VideoRepo;

import jakarta.annotation.PostConstruct;


@Service
public class VideoServiceImpl implements VideoService{

	@Value("${files.video}")
	String DIR;
	
	@Autowired
    VideoRepo videoRepo;
	@PostConstruct
	public void init() {
		File file=new File(DIR);
		
		if(!file.exists()) {
			file.mkdir();
			System.out.println("Folder not created");
		}else {
			System.out.println("Folder already created");
		}
	}
	
	@Override
	public Video saveVideo(Video video, MultipartFile file) {
	  
//		Original filename
		try {
			String filename= file.getOriginalFilename();
			String contentType= file.getContentType();
			InputStream inputStream= file.getInputStream();
			
			 //File path- create
			String cleanFileName = StringUtils.cleanPath(filename);
//			Folder path
			String cleanFolder = StringUtils.cleanPath(DIR);
			
//			Folder path with filename
			Path path=Paths.get(cleanFolder,cleanFileName);
			System.out.println(path);
			
			Files.copy(inputStream, path,StandardCopyOption.REPLACE_EXISTING);
		
		    video.setContentType(contentType);
		    video.setFilePath(path.toString());
		    
		    return videoRepo.save(video);
		    
		}catch(IOException e) {
			e.printStackTrace();
			return null;
		}
		
//		folder path with filename
		
//		copy file to folder
		
//		video meta data
		
//		save metadata
		
	}

	@Override
	public Video getVideobyId(String videoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Video getVideoByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Video> getAll() {

		return videoRepo.findAll();
	}

	@Override
	public void deleteVideo(String videoId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Video updateVideo(Video video, String videoId) {
		// TODO Auto-generated method stub
		return null;
	}

}
