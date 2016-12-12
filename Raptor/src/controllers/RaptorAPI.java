package controllers;

import java.util.List;

import models.Movie;
import models.Rating;
import models.User;

public interface RaptorAPI {
	boolean addUser(String firstName,String lastName,int age,String gender,String occupation);
	boolean removeUser(long userID);
	boolean addMovie(String title,String year,String url);
	boolean addRating(long userID,long movieID,int rating);
	Movie getMovie(long movieID);
	User getUser(long userID);
	List<User> getUsers();
	List<Movie> getMovies();
	List<Rating> getRatings();
	List<Rating> getUserRatings(long userID);
	List<Movie> getUserRecommendations(long userID);
	List<Movie> getTopTenMovies();
	void load() throws Exception;
	void write()throws Exception;
}
