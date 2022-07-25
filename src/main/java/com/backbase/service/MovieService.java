package com.backbase.service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backbase.dto.Movie;
import com.backbase.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MovieService {

	@Autowired
	private MovieRepository movieRepository ;
	
	public Movie getMovie(String title) {
		return movieRepository.findByNomineeIgnoreCaseAndWonIgnoreCase(title, "YES").stream().findAny()
				              .orElseThrow(() ->  new RuntimeException("Movie not present in DB"));
	}

	public boolean postRating(long id, int rating) {
		Optional<Movie> movie = movieRepository.findById(id);
		movie.ifPresentOrElse(x -> {  int totalVotes = x.getTotalVotes() +1; 
		                              double averageRating = x.getRatings().doubleValue() *  x.getTotalVotes() ;
		                              averageRating = (averageRating + rating)  / totalVotes;
			                          x.setRatings(BigDecimal.valueOf(averageRating));
		                              x.setTotalVotes(totalVotes);
		                              movieRepository.save(x);}, 
				                                          () -> new Exception("Movie not present in DB"));
		return true;
	}

	public List<Movie> getAllTopRatedMovies() {
		return movieRepository.findTop10ByRatingsNotOrderByRatingsDesc(BigDecimal.ZERO).stream()
				              .sorted(Comparator.comparing(Movie::getBoxOfficeCollection).reversed())
				              .collect(Collectors.toList());
	}
	
}
