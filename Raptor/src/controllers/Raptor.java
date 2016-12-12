package controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

//import utils.Serializer;
import models.Movie;
import models.Rating;
import models.User;
import utils.Serializer;

public class Raptor implements RaptorAPI{
	
	private List<User> users;
	private List<Movie> movies;
	private List<Rating> ratings;
	private Serializer s;
	
	public Raptor(Serializer s) throws Exception
	{
		users = new ArrayList<User>();
		movies = new ArrayList<Movie>();
		ratings = new ArrayList<Rating>();
		if(s == null)
		{
			loadUsers();
			loadMovies();
			loadRatings();
		}
		else
		{
			this.s = s;
			load();
		}
	}

	private void loadUsers()throws Exception
	{
        File usersFile = new File("Data/users.dat");
        Scanner inUsers = new Scanner(usersFile);
          //each field is separated(delimited) by a '|'
        String delims = "[|]";
        while (inUsers.hasNextLine()) {
            // get user and rating from data source
            String userDetails = inUsers.nextLine().trim();

            // parse user details string
            String[] userTokens = userDetails.split(delims);
            
            if (userTokens.length == 7) {
            	String firstName = userTokens[1];
            	String lastName = userTokens[2];
		    	int age =Integer.parseInt(userTokens[3]);
		    	String gender = userTokens[4];
		    	String occupation = userTokens[5];
		    	User user;
		    	try
		    	{
		    		user = new User(firstName, lastName, age, gender, occupation);
		    		users.add(user);
		    	}
		    	catch(Exception e)
		    	{
		    		System.out.println("Cant add user");
		    		e.printStackTrace();
		    	}
            }
        }
	}
	
	private void loadMovies()throws Exception
	{
		File moviesFile = new File("Data/items.dat");
        Scanner inMovies = new Scanner(moviesFile);
          //each field is separated(delimited) by a '|'
        String delims = "[|]";
        while (inMovies.hasNextLine()) {
            // get user and rating from data source
            String moviesDetails = inMovies.nextLine().trim();

            // parse user details string
            String[] movieTokens = moviesDetails.split(delims);
            
            if (movieTokens.length == 23) {
            	String title = movieTokens[1];
            	String year = movieTokens[2];
            	String url = movieTokens[3];
		    	Movie movie;
		    	try
		    	{
		    		movie = new Movie(title,year,url);
		    		movies.add(movie);
		    	}
		    	catch(Exception e)
		    	{
		    		System.out.println("Cant add movie");
		    		e.printStackTrace();
		    	}
            }
        }
	}
	
	private void loadRatings() throws Exception
	{
		File ratingsFile = new File("Data/ratings.dat");
        Scanner inRatings = new Scanner(ratingsFile);
          //each field is separated(delimited) by a '|'
        String delims = "[|]";
        while (inRatings.hasNextLine()) {
            // get user and rating from data source
            String ratingsDetails = inRatings.nextLine().trim();

            // parse user details string
            String[] ratingsTokens = ratingsDetails.split(delims);
            
            if (ratingsTokens.length == 4) {
            	long userID = Long.parseLong(ratingsTokens[0]);
            	long movieID = Long.parseLong(ratingsTokens[1]);
		    	int rating =Integer.parseInt(ratingsTokens[2]);
		    	Rating rt;
		    	try
		    	{
		    		 rt = new Rating(userID,movieID,rating);
		    		 ratings.add(rt);
		    	}
		    	catch(Exception e)
		    	{
		    		System.out.println("Cant add rating");
		    		e.printStackTrace();
		    	}
            }
        }
	}

	@Override
	public boolean addUser(String firstName, String lastName, int age, String gender, String occupation)
	{
		User user;
		try{
			user = new User(firstName,lastName,age,gender,occupation);
		}catch(IllegalArgumentException e)
		{
			return false;
		}
		users.add(user);
		return true;
	}

	@Override
	public boolean removeUser(long userID) {
		try{
			users.remove((int)(userID+1));
		}
		catch(IllegalArgumentException e)
		{
			return false;
		}
		return true;
	}

	@Override
	public boolean addMovie(String title, String year, String url){
		Movie movie;
		try{
			movie = new Movie(title,year,url);
		}catch(Exception e)
		{
			return false;
		}
		movies.add(movie);
		return true;
	}

	@Override
	public boolean addRating(long userID, long movieID, int rating) {
		Rating rt;
		try{
			rt = new Rating(userID,movieID,rating);
		}catch(IllegalArgumentException e)
		{
			return false;
		}
		ratings.add(rt);
		return true;
	}

	@Override
	public Movie getMovie(long movieID) {
		return movies.get((int) (movieID+1));
	}

	@Override
	public User getUser(long userID) {
		return users.get((int) (userID+1));
	}

	@Override
	public List<User> getUsers() {
		return users;
	}

	@Override
	public List<Movie> getMovies() {
		return movies;
	}
	
	@Override
	public List<Rating> getRatings()
	{
		return ratings;
	}

	@Override
	public List<Rating> getUserRatings(long userID) {
		List<Rating> userRatings = new ArrayList<Rating>();
		for(int i=0;i<ratings.size();i++)
		{
			if(ratings.get(i).getUserID() == userID)
				userRatings.add(ratings.get(i));
		}
		return userRatings;
	}

	@Override
	public List<Movie> getUserRecommendations(long userID) {
		List<Movie> userRecommendations = new ArrayList<Movie>();
		for(Rating rating : ratings)
		{
			if(!getUserRatings(userID).contains(rating))
			{
				for(Rating userRating : getUserRatings(userID))
				{
					if(userRating.getMovieID() != rating.getMovieID() && rating.getRating()>4)
					{
						userRecommendations.add(getMovie(rating.getMovieID()));
					}
				}
			}
		}
		return userRecommendations;
	}

	@Override
	public List<Movie> getTopTenMovies() {
		List<Movie> topTenMovies = new ArrayList<Movie>();
		for(Rating rating : ratings)
		{
			if(rating.getRating() > 4 && !topTenMovies.contains(getMovie(rating.getMovieID())))
				topTenMovies.add(getMovie(rating.getMovieID()));
			if(topTenMovies.size() >= 10)
				break;
		}
		return topTenMovies;
	}

	@Override
	public void load() throws Exception 
	{
		s.push(new Long(Movie.getCounter()));
		s.push(new Long(User.getCounter()));
		s.push(users);
		s.push(movies);
		s.push(ratings);
		s.write();
	}

	@Override
	public void write() throws Exception 
	{
		s.read();
		ratings = (List<Rating>)s.pop();
		movies = (List<Movie>)s.pop();
		users = (List<User>)s.pop();
		User.setCounter((Long)(s.pop()));
		Movie.setCounter((Long)(s.pop()));
	}
}
