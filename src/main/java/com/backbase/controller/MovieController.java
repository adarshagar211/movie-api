package com.backbase.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backbase.dto.Movie;
import com.backbase.service.MovieService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MovieController {

	@Autowired
	private MovieService movieService;

	@GetMapping("/movies")
	public ResponseEntity<?> getAll(@RequestParam String title) {
		log.info("Getting all Movies present in IMDB database");
		try {
			Movie movies = movieService.getMovie(title);
			return ResponseEntity.ok(movies);
		} catch (Exception ex) {
			log.debug("Error while retriving data from DB ", ex);
			return ResponseEntity.internalServerError().body("Movie is not present in DB");
		}
	}

	// Post rating for movie
	@PostMapping("/movies/{id}")
	public ResponseEntity<?> postRating(@PathVariable long id, @RequestParam int rating) {
		log.info("Post rating for the movie");
		try {
			boolean status = movieService.postRating(id, rating);
			return ResponseEntity.ok(status);
		} catch (Exception ex) {
			log.debug("Error while retriving data from DB ", ex);
			return ResponseEntity.internalServerError().body("Movie is not present in DB");
		}
	}

	// Get All Top Rated Movies from DB
	@GetMapping("/movies/topRated")
	public ResponseEntity<?> getAllTopRatedMovies() {
		log.info("Get All Top 10 Rated Movies from DB ");
		try {
			List<Movie> status = movieService.getAllTopRatedMovies();
			return ResponseEntity.ok(status);
		} catch (Exception ex) {
			log.debug("Error while retriving data from DB ", ex);
			return ResponseEntity.internalServerError().build();
		}
	}
}
