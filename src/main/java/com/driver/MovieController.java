package com.driver;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/*Pair an existing movie and director: PUT /movies/add-movie-director-pair
Pass movie name and director name as request parameters
Return success message wrapped in a ResponseEntity object
Controller Name - addMovieDirectorPair*/


@RequestMapping("/movies")
@Controller
public class MovieController {


    MovieService movieService= new MovieService();
    @PostMapping("/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie){
        String answer=movieService.addMovie(movie);
        return new ResponseEntity(answer, HttpStatus.CREATED);
    }
    @PostMapping("/add-director")
    public ResponseEntity addDirector(@RequestBody Director director){
        String answer=movieService.addDirector(director);
        return new ResponseEntity(answer,HttpStatus.CREATED);
    }
    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity getMovieByname(@PathVariable String name){
        Optional<Movie> op=movieService.getMovieByName(name);
        if(op.isPresent()){
            return new ResponseEntity(op.get(),HttpStatus.FOUND);
        }
        return new ResponseEntity("Movie Not found",HttpStatus.NOT_FOUND);
    }
    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity getDirectorByname(@PathVariable String name){
        Optional<Director> op=movieService.getDirectorByName(name);
        if(op.isPresent()){
            return new ResponseEntity(op.get(),HttpStatus.FOUND);
        }
        return new ResponseEntity("Director Not found",HttpStatus.NOT_FOUND);
    }
    @GetMapping("/get-movies-by-director-name/{name}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable String name){
        List<String> movieList=movieService.getMoviesByDirectorName(name);
        return new ResponseEntity(movieList,HttpStatus.FOUND);
    }
    @GetMapping("/get-all-movies")
    public ResponseEntity findAllMovies(){
        List<String> movieList=movieService.findAllMovies();
        return new ResponseEntity(movieList,HttpStatus.FOUND);
    }
    @PutMapping("/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam String directorName,@RequestParam String movieName){
        String answer=movieService.addMovieDirectorPair(directorName,movieName);
        return new ResponseEntity(answer,HttpStatus.CREATED);
    }
//    Delete a director and its movies from the records: DELETE /movies/delete-director-by-name
//    Pass director’s name as request parameter
//    Return success message wrapped in a ResponseEntity object
//    Controller Name - deleteDirectorByName
//
//    Delete all directors and all movies by them from the records: DELETE /movies/delete-all-directors
//    No params or body required
//    Return success message wrapped in a ResponseEntity object
//    Controller Name - deleteAllDirectors
    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam String name){
        String answer=movieService.deleteDirectorByName(name);
        return new ResponseEntity(answer,HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/delete-all-directors")
    public ResponseEntity deleteAllDirectors(){
        String answer=movieService.deleteAllDirectors();
        return new ResponseEntity(answer,HttpStatus.ACCEPTED);
    }
}
