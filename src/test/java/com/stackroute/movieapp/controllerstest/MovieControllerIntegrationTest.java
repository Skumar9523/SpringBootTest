package com.stackroute.movieapp.controllerstest;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import com.stackroute.movieapp.MovieAppApplication;
import com.stackroute.movieapp.domain.Movie;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MovieAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieControllerIntegrationTest {

	 @LocalServerPort
	    private int port;

	    Movie movie;

	    private TestRestTemplate restTemplate = new TestRestTemplate();
	    HttpHeaders headers = new HttpHeaders();
	    HttpEntity<Movie> entity;

	    private String createURLWithPort(String uri) {
	        return "http://localhost:" + port + uri;
	    }

	    @Before
	    public void setUp() throws Exception {
	        
	        movie = new Movie(1, "Batman", "very good movie", "xyz", "2017");
	        entity = new HttpEntity<Movie>(movie, headers);

	    }

	    @After
	    public void tearDown() throws Exception {
	        restTemplate.exchange(createURLWithPort("/api/movie/1"), HttpMethod.DELETE, entity, String.class);
	    }
}
