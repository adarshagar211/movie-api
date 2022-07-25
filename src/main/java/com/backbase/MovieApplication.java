package com.backbase;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.backbase.dto.Movie;
import com.backbase.repository.MovieRepository;
import com.opencsv.CSVReader;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@Slf4j
public class MovieApplication {
	
	private static final String MOVIE_FILTER ="Best Picture";

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(MovieApplication.class, args);
		MovieRepository movieRepository =ctx.getBean(MovieRepository.class);
		moviesDatabaseSetUp(movieRepository);
	}
	
	private static void moviesDatabaseSetUp(MovieRepository movieRepository) {
		CSVReader reader = null;
		List<Movie> moviesList = new ArrayList<>();
		log.info("Reading from csv file and adding movies data in db");
		try {
			// parsing a CSV file into CSVReader class constructor
			InputStream inputStream = MovieApplication.class.getClassLoader().getResourceAsStream("academy_awards.csv");
			reader = new CSVReader(new InputStreamReader(inputStream));
			String[] nextLine;
			int count = 0 ; 
			// reads one line at a time
			while ((nextLine = reader.readNext()) != null) {
				if (MOVIE_FILTER.contains(nextLine[1])) {
					Movie movie = Movie.builder().yearNomination(nextLine[0]).category(nextLine[1]).nominee(nextLine[2])
							.addInfo(nextLine[3]).won(nextLine[4]).build();
					if(count++ > 100 && count < 200) {
						movie.setBoxOfficeCollection(count * 1.2);
						movie.setRatings(BigDecimal.valueOf((count * 1.2) % 10.00));
						movie.setTotalVotes(count);
					}
					moviesList.add(movie);
				}
			}
			movieRepository.saveAll(moviesList);
			log.info(" All data persisted in db");
		} catch (Exception e) {
			log.debug(" Error while persisting movie data in db : {}", e.getMessage());
		}
	}
}
