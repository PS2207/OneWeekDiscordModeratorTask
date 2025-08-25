package com.videostreaming.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.videostreaming.app.entities.Video;

@Repository
public interface VideoRepo extends JpaRepository<Video, String>{
  
	Optional<Video> findByTitle(String title);
}
