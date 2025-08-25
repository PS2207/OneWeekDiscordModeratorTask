package com.videostreaming.app.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="youtube_videos")
public class Video {
  @Id
  private String videoId;
  private String title;
  private String description;
  private String contentType;
  private String filePath;
  
  // Child → Parent
//  @ManyToOne
//  @JoinColumn(name = "course_id", nullable = false) // foreign key in videos table(primary key in course tabel)
//  private Course course;

  
}

  