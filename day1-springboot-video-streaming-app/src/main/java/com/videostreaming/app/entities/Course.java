package com.videostreaming.app.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


/* Represents a course entity in the video streaming app.
 * 
 * A course can have multiple videos associated with it.
 * but The relationship is unidirectional - i.e.,we can  navigate from course to its videos
 * but not from video back to course.
 * For Unidirectional - use @OneToMany ,  BiDirectional - use @ManyToOne
 * Analogy:
 * Think of @ManyToOne (Video → Course) as “child knows its parent”.
 * Think of @OneToMany (Course → Videos) as “parent knows all its children”.
 */

//How to store video Metadata(data about data like id,title of course,video) in database
@Entity
@Table(name="youtube_courses")
public class Course {
  @Id
  private String courseId;
  private String courseTitle;

  /*
   * List of videos that belong to this course.
   * One-to-many relationship (unidirectional). for bidirectional we can use @ManyToOne @JoinedColumn()
   */
//  @OneToMany(mappedBy="course")
//  private List<Video> list = new ArrayList<>();

}
