package com.videostreaming.app.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.videostreaming.app.entities.Video;
import com.videostreaming.app.payload.ApiResponse;
import com.videostreaming.app.services.VideoService;

@RestController
@RequestMapping("/api/v1/videos")

//Springboot controller will allow backend to accept requests from any frontend origin(react,angular,vue etc) 
//but no need to use @CrossOrigin on every controller rather.
//In Spring Boot the safest way to allow multiple frontend- 'Configure CORS globally' by creating configuration class instead of using "*" on each controller.
//"*" this allows all frontend origin 
//@CrossOrigin("*")
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
				.body(ApiResponse.builder()
						.message("video not upload")
						.success(false)
						.build());
		}
	}
	
//	get all video
	@GetMapping
	public List<Video> getAllVideos(){
		return videoService.getAll();
	}
	
//	getBy id
	@GetMapping("/{videoId}")
	public ResponseEntity<Video> get(@PathVariable String videoId){
		Video video = videoService.getVideoById(videoId);
		return ResponseEntity.ok(video);

	}
	//video stream-http://localhost:8081/api/v1/videos/stream/videoId
	@GetMapping("/stream/{videoId}")
	public ResponseEntity<Resource> stream(@PathVariable String videoId){
	  Video  video= videoService.getVideoById(videoId);
	  String contentType = video.getContentType();
	  String filePath = video.getFilePath();	  
	  Resource resource= new FileSystemResource(filePath);
	  
	  if(contentType==null) {
		  contentType= "applicatoin/octet-stream";
	  }
	  return ResponseEntity
			  .ok()
			  .contentType(MediaType.parseMediaType(contentType))
			  .body(resource);
	}
	
	//delete
	@DeleteMapping("/delete/{videoId}")
	public ResponseEntity<?> delete(@PathVariable String videoId){
		videoService.deleteVideo(videoId);
		return ResponseEntity.ok().body("Video deleted successfully!"); 
	}
	
	
    //Update video 
    @PutMapping("/update/{id}")
    public ResponseEntity<Video> updateVideo(
            @RequestBody Video video,
            @PathVariable("id") String videoId) {
        
        Video updatedVideo = videoService.updateVideo(video, videoId);
        return ResponseEntity.ok(updatedVideo);
    }
	
}
  


	
	