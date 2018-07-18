package com.stackroute.movieapp.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.movieapp.domain.Movie;
@Repository
public interface MovieRepository extends MongoRepository<Movie, Integer>{
    public List<Movie> findByTitle(String title);
}