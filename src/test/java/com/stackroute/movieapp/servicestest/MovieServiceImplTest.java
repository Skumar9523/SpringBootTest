package com.stackroute.movieapp.servicestest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.stackroute.movieapp.domain.Movie;
import com.stackroute.movieapp.exceptions.MovieAlreadyExistsException;
import com.stackroute.movieapp.repositories.MovieRepository;
import com.stackroute.movieapp.services.MovieServiceImpl;

/**
* test class for MovieServiceImpl
*/
public class MovieServiceImplTest {
    /**
     * mocking the MovieRepository
     */
    @Mock
    private transient MovieRepository movieRepo;
    private transient Movie movie;

    /**
     * injecting mocks in MovieServiceImpl object
     */
    @InjectMocks
    private transient MovieServiceImpl movieServiceImpl;

    /**
     * variable to hold user defined movies list
     */
    private transient Optional<Movie> options;

    /**
     * Initializing the declarations
     */
    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
        movie = new Movie(1, "POC", "good Movie", "www.abc.com", "2015-03-31");
        options = Optional.of(movie);

    }

    /**
     * testing mock creation
     */
    @Test
    public void testMockCreation() {
        assertNotNull("jpaRepository creation fails: use @injectMocks on movieServicempl", movieRepo);
    }

    /**
     * testing the save method
     * @throws MovieAlreadyExistsException
     */
    @Test
    public void testSaveMovie() throws MovieAlreadyExistsException{
        when(movieRepo.save(movie)).thenReturn(movie);
        Movie status = movieServiceImpl.saveMovie(movie);
        assertEquals(movie,status);
        verify(movieRepo,times(1)).save(movie);        
    }
	
}