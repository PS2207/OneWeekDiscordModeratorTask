package com.videostreaming.app.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.videostreaming.app.entities.Video;
import com.videostreaming.app.payload.CustomMessage;
import com.videostreaming.app.services.VideoService;

@RestController
@RequestMapping("/api/v1/videos")
public class VideoController {
	@Autowired
	VideoService videoService;

	@PostMapping
	public ResponseEntity<?> create(@RequestParam("file")MultipartFile file, 
			@RequestParam("title") String title, @RequestParam("description") String description){
		
		Video video=new Video();
		video.setTitle(title);
		video.setDescription(description);
		video.setVideoId(UUID.randomUUID().toString());
		Video savedVideo= videoService.saveVideo(video, file);
		
		if(savedVideo!=null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(video);
		}else {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(CustomMessage.builder()
						.message("video not upload")
						.success(false)
						.build());
		}
	}
	
	@GetMapping
	public List<Video> getAll(){
		return videoService.getAll();
	}
	
}
