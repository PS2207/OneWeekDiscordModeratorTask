package com.videostreaming.app.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.videostreaming.app.entities.Video;

public interface VideoService {

	
	//save video
	Video saveVideo(Video video, MultipartFile file);
	
	//get video by id
	Video getVideobyId(String videoId);
	
	//get video by title
	Video getVideoByTitle(String title);
	
	//get all videos
	List<Video> getAll();
	
	//delete video
	void deleteVideo(String videoId);
	
   //update video
	Video updateVideo(Video video, String videoId);
}
