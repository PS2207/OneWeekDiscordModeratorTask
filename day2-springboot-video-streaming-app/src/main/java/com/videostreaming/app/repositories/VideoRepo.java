package com.videostreaming.app.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.videostreaming.app.entities.Video;

@Repository
public interface VideoRepo extends JpaRepository<Video, String>{
	Video findByTitle(String title);

//	Optional<Video> delete(String videoId);//jpa repo already has deleteById & delete method so no need this 
}
