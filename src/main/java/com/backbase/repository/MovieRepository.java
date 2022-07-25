package com.backbase.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backbase.dto.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{
  
	public List<Movie> findByNomineeIgnoreCaseAndWonIgnoreCase(String title, String won);
	
	public List<Movie> findTop10ByRatingsNotOrderByRatingsDesc(BigDecimal rating);

}
