package com.project.moviebookingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.moviebookingsystem.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{
	 @Query(value = "select * from movie m where m.moviename like %:keyword%", nativeQuery = true)
	 List<Movie> findByKeyword(@Param("keyword") String keyword);
	 @Query(value = "select * from movie m where m.moviename like %:keyword% limit 1", nativeQuery = true)
	 Movie findByMovieName(@Param("keyword") String keyword);
}
