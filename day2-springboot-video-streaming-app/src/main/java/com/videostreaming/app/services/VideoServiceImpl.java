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
import com.videostreaming.app.exception.ResourceNotFoundException;
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
	public Video getVideoById(String videoId) {
		Video video = videoRepo.findById(videoId).orElseThrow(()-> new ResourceNotFoundException("Video" , "Id " , videoId));
		return video;
	}

	@Override
	public Video getVideoByTitle(String title) {
		return videoRepo.findByTitle(title);
	}

	@Override
	public List<Video> getAll() {
		return videoRepo.findAll();
	}

	@Override
	public void deleteVideo(String videoId) {
		//recommended way
		Video video = videoRepo.findById(videoId).orElseThrow(()->new ResourceNotFoundException("Video", "Id", videoId));
		videoRepo.delete(video);
//		OR (using map, a bit concise but less readable
//		Video video= videoRepo.findById(videoId).map(v->{videoRepo.delete(v); return v;}).orElseThrow(()->new RuntimeException("Vidoe not found"));

		
	}
//	If we use Postgres → we can write a native query with RETURNING.
//	If we use MySQL (most common) → we must fetch first, then delete.
	
	@Override
	public Video updateVideo(Video video, String videoId) {
	    // Step 1: Find existing video
	    Video existingVideo = videoRepo.findById(videoId)
	            .orElseThrow(() -> new ResourceNotFoundException("Video", "id", videoId));

	    // Step 2: Update fields (only those that should be editable)
	    existingVideo.setTitle(video.getTitle());
	    existingVideo.setDescription(video.getDescription());
	    existingVideo.setContentType(video.getContentType());
	    existingVideo.setFilePath(video.getFilePath());

	    //If you also allow file replacement, handle MultipartFile separately here

	    // Step 3: Save updated video
	    return videoRepo.save(existingVideo);
	}

}
