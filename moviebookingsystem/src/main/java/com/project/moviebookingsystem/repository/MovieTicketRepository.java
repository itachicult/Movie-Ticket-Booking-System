package com.project.moviebookingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.moviebookingsystem.model.movieticket;

public interface MovieTicketRepository extends JpaRepository<movieticket, Long> {
	 @Query(value = "select * from movieticket m where m.user like %:keyword%", nativeQuery = true)
	 List<movieticket> findByKeyword(@Param("keyword") String keyword);
}
