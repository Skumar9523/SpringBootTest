package com.stackroute.movieapp.controllerstest;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.stackroute.movieapp.controllers.MovieController;
import com.stackroute.movieapp.domain.Movie;
import com.stackroute.movieapp.services.MovieServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
public class MovieControllerTest {
	@Autowired
    private MockMvc movieMockMvc;
	
	@MockBean
	private MovieServiceImpl movieServiceImpl;
	@InjectMocks
	private MovieController movieController = new MovieController(movieServiceImpl);

	private Movie movie;
	
	//private int id;
	//private String title;
	
	@Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        movieMockMvc = MockMvcBuilders.standaloneSetup(movieController).build();    }


	@Test
	public void testSaveMovie() {
		
//		  when(movieServiceImpl.saveMovie(any())).thenReturn(movie);
//	        movieController.saveMovie(movie);
//	        verify(movieServiceImpl,times(1)).saveMovie(movie);
		
		//fail("Not yet implemented");
	}

	@Test
	public void testGetAllMovies() throws Exception{
		  when(movieServiceImpl.getAllMovies()).thenReturn(null);
          movieMockMvc.perform(get("/api/v1/movies"))
         .andExpect(status().isOk());
	}

	@Test
	public void testGetMovieById() throws Exception {
		

		 Movie movie=new Movie();
         movie.setId(1);
         movie.setTitle("asd");
         movie.setImdbId("wer");
         movie.setPoster("dfgh");
         movie.setYear("2019");
         int id=1;
         Optional<Movie> opt = Optional.of(movie);
         when(movieServiceImpl.getMovieById(id)).thenReturn(opt);
         movieMockMvc.perform(get("/api/v1/movie/" + id))
         .andExpect(status().isOk());
		
		
//		  when(movieServiceImpl.getMovieById(1)).thenReturn(null);
//          movieMockMvc.perform(get("/api/v1/movie/1"))
//          .andExpect(status().isOk());
		
		
		//fail("Not yet implemented");
	}

	@Test
	public void testGetMovieByTitle() throws Exception {
				
		 when(movieServiceImpl.getMovieByTitle("123")).thenReturn(null);
		 movieMockMvc.perform(get("/api/v1/movieByTitle/123"))
		.andExpect(status().isOk());
		
		//fail("Not yet implemented");
	}

	@Test
	public void testDeleteMovieById() throws Exception {
		  when(movieServiceImpl.getMovieById(movie.getId())).thenReturn(null);
	        movieMockMvc.perform(delete("/api/v1/movie/{id}",movie.getId())).andExpect(status().isOk());
	        verify(movieServiceImpl,times(1)).deleteMovie(movie.getId());
		
				
		//fail("Not yet implemented");
	}

	@Test
	public void testUpdateMovie() throws Exception {
		
		Movie movie1=new Movie(7,"hfhj", "two","vbnc","2000");
		  when(movieServiceImpl.updateMovie(movie1, movie.getId())).thenReturn(null);
          movieMockMvc.perform(get("/api/v1/movies" ,+movie.getId()))
         .andExpect(status().isOk());
		
		
		//fail("Not yet implemented");
	}
	
	
    

}
