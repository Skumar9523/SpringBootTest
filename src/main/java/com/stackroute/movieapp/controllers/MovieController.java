package com.stackroute.movieapp.controllers;

import java.util.List;
import java.util.Optional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.movieapp.domain.Movie;
import com.stackroute.movieapp.exceptions.MovieAlreadyExistsException;
import com.stackroute.movieapp.exceptions.MovieNotFoundException;
import com.stackroute.movieapp.services.MovieServiceImpl;

@RestController
@RequestMapping("/api/v1")
public class MovieController {

	private MovieServiceImpl movieService;
	private Environment env;
	private final Logger logger= LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public	MovieController(MovieServiceImpl movieService){
		this.movieService = movieService;
		this.env=env;
	}
	
	@PostMapping("/movie")
	public ResponseEntity<?> saveMovie(@RequestBody Movie movie) {
		try {
			logger.info("This is an info message");	// information is passed
			return new ResponseEntity<Movie> (movieService.saveMovie(movie), HttpStatus.CREATED);
		} catch (MovieAlreadyExistsException e) {
			logger.error("This is an error message");
			return new ResponseEntity<String> ("Movie not found ", HttpStatus.CONFLICT);
			//			return new ResponseEntity<String> (e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/movies")
	public ResponseEntity<?> getAllMovies() {
		  
		        logger.debug("This is a debug message");
		        logger.info("This is an info message");
		        logger.warn("This is a warn message");
		        logger.error("This is an error message");
		        
		    
		return new ResponseEntity<Iterable<Movie>> (movieService.getAllMovies(), HttpStatus.OK);
	}
	
	@GetMapping("/movie/{id}")
	public ResponseEntity<?> getMovieById(@PathVariable int id) {
		return new ResponseEntity<Optional<Movie>> (movieService.getMovieById(id), HttpStatus.OK);
	}
	
	@GetMapping("/movieByTitle/{title}")
	public ResponseEntity<?> getMovieByTitle(@PathVariable String title) {
		try {logger.info("This is an info message");
        return new ResponseEntity<List<Movie>> (movieService.getMovieByTitle(title), HttpStatus.OK);
    } catch (MovieNotFoundException e) {
        // TODO Auto-generated catch block
        logger.error("This is an error message");
        return new ResponseEntity<String> (e.getMessage(), HttpStatus.BAD_REQUEST);
    }
	}
	
	@DeleteMapping("/movie/{id}")
	public ResponseEntity<?> deleteMovieById(@PathVariable int id) {
		movieService.deleteMovie(id);
		return new ResponseEntity<String> ("Deleted", HttpStatus.OK);
	}
	
	@PutMapping("/movie/{id}")
	public ResponseEntity<?> updateMovie(@PathVariable int id, @RequestBody Movie movie) {
        logger.info("This is an info message");
		return new ResponseEntity<Movie> (movieService.updateMovie(movie, id), HttpStatus.OK);
	}
}
