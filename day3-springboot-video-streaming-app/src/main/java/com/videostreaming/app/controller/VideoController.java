package com.videostreaming.app.controller;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRange;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.videostreaming.app.AppConstants;
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
		  contentType= "applicatoin/octet-stream";//if noby default
	  }
	  return ResponseEntity
			  .ok()
			  .contentType(MediaType.parseMediaType(contentType))//send video as resource
			  .body(resource);
	}

	
	
	
	@GetMapping("/stream/range/{videoId}")
	public ResponseEntity<Resource> streamVideoRange(
			@PathVariable String videoId,
			@RequestHeader(value="Range",required=false) String range){
		System.out.println(range);
		Video video=videoService.getVideoById(videoId);
		Path path= Paths.get(video.getFilePath());
		Resource resource= new FileSystemResource(path);
		String contentType=(video.getContentType() != null) ? video.getContentType() : "application/octet-stream";

		
		if(contentType==null) {
			contentType="application/octet-stream";
		}
		//file length
		long fileLength=path.toFile().length();
		
		if(range==null) {
			return ResponseEntity.ok()
					.contentType(MediaType.parseMediaType(contentType))
					.contentLength(fileLength)
					.body(resource);
		}
		
		//calculating start and end range
		long rangeStart;
		long rangeEnd;
		String[] ranges = range.replace("bytes=", "").split("-");
		rangeStart = Long.parseLong(ranges[0]);
		
		rangeEnd=rangeStart + AppConstants.CHUNK_SIZE-1;
		if(rangeEnd>=fileLength) {
			rangeEnd=fileLength-1;
		}
//		if(range.length() > 1) {
//			rangeEnd = Long.parseLong(ranges[1]);
//		}else {
//			rangeEnd= fileLength-1;
//		}
//		
//		if(rangeEnd>fileLength-1) {
//			rangeEnd=fileLength-1;
//		}
		
		InputStream inputStream;
		try {
		   inputStream=Files.newInputStream(path);
		   inputStream.skip(rangeStart);		   
		   long contentLength= rangeEnd - rangeStart + 1;
		   
		   byte[] data = new byte[(int) contentLength];
		   int read = inputStream.read(data, 0, data.length);
		// Console logging
		   System.out.println("----------------------------------------------------");
		   System.out.println("📌 Incoming Range Header : " + range);
		   System.out.println("➡ File: " + path.getFileName());
		   System.out.println("➡ File length: " + fileLength + " bytes");
		   System.out.println("➡ Serving bytes " + rangeStart + " - " + rangeEnd);
		   System.out.println("➡ Chunk size to send: " + contentLength + " bytes");
		   System.out.println("✅ Actually read (bytes): " + read);
		   System.out.println("----------------------------------------------------");
		   
			HttpHeaders headers=new HttpHeaders();
			headers.add("Content-Range","bytes " + rangeStart + "-" + rangeEnd + "/" +fileLength);
			headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
			headers.add("Pragma","no-cache");
			headers.add("Expires","0");
			headers.add("X-Content-Type-Options","nosniff");
			headers.setContentLength(contentLength);
			
			return ResponseEntity
					.status(HttpStatus.PARTIAL_CONTENT)
	                .headers(headers)
	                .contentType(MediaType.parseMediaType(contentType))
	                .body(new ByteArrayResource(data));
		   
		}catch(IOException ex) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
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
            @RequestParam ("file") MultipartFile file,
            @RequestParam ("title") String title,
            @RequestParam ("description") String description,
            @PathVariable("id") String videoId) {


        Video updatedVideo = videoService.updateVideo(videoId, title, description, file);
        return ResponseEntity.ok(updatedVideo);
    }
	
//    @RequestBody means Spring expects JSON,
//    & if we give @RequestBody for multipart/form-data,
//    so spring throws 415 Unsupported Media Type.
}
  


	
	