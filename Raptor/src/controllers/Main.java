package controllers;

import utils.Serializer;
import utils.XMLSerializer;

import java.io.File;
import java.util.Scanner;

import models.*;

public class Main {
	
	private RaptorAPI raptor;
	private Scanner s;
	
	public static void main(String[] args) throws Exception
	{
		new Main();
	}
	
	public Main() throws Exception
	{
		File file = new File("data.xml");
		this.s = new Scanner(System.in);
		Serializer s = null;
		if(file.exists())
			s = new XMLSerializer(file);
		raptor = new Raptor(s);
		
		run();
	}
	
	private int showMenu()
	{
		System.out.println("1. Show All Users");
		System.out.println("2. Show All Movies");
		System.out.println("3. Add User");
		System.out.println("4. Remove User");
		System.out.println("5. Add Movie");
		System.out.println("6. Add Rating");
		System.out.println("7. Top Ten Movies");
		System.out.println("8. User Recommendations");
		System.out.println("9. Get User Ratings");
		System.out.println("0. Exit");
		int c = -1;
		try
		{
			c = s.nextInt();
		}
		catch(Exception e)
		{
			
		}
		return c;
	}
	
	public void run() throws Exception
	{
		int choice = showMenu();
		
		while(choice != 0)
		{
			switch(choice)
			{
			case 1:
				showAllUsers();
				break;
			case 2:
				showAllMovies();
				break;
			case 3:
				addUser();
				break;
			case 4:
				removeUser();
				break;
			case 5: 
				addMovie();
				break;
			case 6:
				addRating();
				break;
			case 7:
				topTen();
				break;
			case 8:
				userRec();
				break;
			case 9:
				userRatings();
				break;
			default:
					System.out.println("WRONG INPUT!");
					break;
			}
			s.nextLine(); // Scanner bug
			choice = showMenu();
		}
		System.out.println("Exiting...");
		raptor.write();
		System.exit(0);
	}

	private void showAllUsers() {
		for(User user : raptor.getUsers())
			System.out.println(user);
		
	}

	private void showAllMovies() {
		for(Movie movie : raptor.getMovies())
			System.out.println(movie);
		
	}

	private void addUser() {
		// TODO Auto-generated method stub
		
	}

	private void removeUser() {
		System.out.print("First Name - ");
		String firstName = s.next();
		System.out.print("Last Name - ");
		String lastName = s.next();
		System.out.print("Age - ");
		int age = 0;
		try{
			age = s.nextInt();
		}catch(Exception e)
		{
			System.out.println("Can't use that age!");
			return; // Stop running this method
		}
		System.out.print("Gender - ");
		String gender = s.next();
		if(!gender.trim().toLowerCase().substring(0).equals("m")||!gender.trim().toLowerCase().substring(0).equals("f"))
		{
			System.out.println("Can't use that gender! There are only two!");
			return; // Stop running this method
		}
		System.out.print("Occupation - ");
		String occupation = s.next();
		if(raptor.addUser(firstName, lastName, age, gender, occupation))
			System.out.println("User added successfully");
		else
			System.out.println("An error has occured and user was not added!");
	}

	private void addMovie() {
		System.out.print("Movie Title - ");
		String title = s.next();
		System.out.print("Year - ");
		String year = s.next();
		System.out.print("URL - ");
		String url = s.next();
		if(raptor.addMovie(title, year, url))
			System.out.println("Movie added successfully");
		else
			System.out.println("An error has occured and movie was not added!");
	}

	private void addRating() {
		System.out.print("User ID - ");
		long userID = 0;
		try{
			userID = s.nextLong();
		}catch(Exception e)
		{
			System.out.println("WRONG USER ID");
			return;//Stop this method
		}
		System.out.print("Movie ID - ");
		long movieID = 0;
		try{
			movieID = s.nextLong();
		}catch(Exception e)
		{
			System.out.println("WRONG MOVIE ID");
			return;//Stop this method
		}
		System.out.print("Rating - ");
		int rating = 0;
		try{
			rating = s.nextInt();
		}catch(Exception e)
		{
			System.out.println("WRONG RATING");
			return;//Stop this method
		}
		if(raptor.addRating(userID, movieID, rating))
			System.out.println("Rating added successfully");
		else
			System.out.println("An error has occured and rating was not added!");
		
	}

	private void topTen() {
		for(Movie movie : raptor.getTopTenMovies())
			System.out.println(movie);
		
	}

	private void userRec() {
		System.out.print("User ID - ");
		long userID = 0;
		try{
			userID = s.nextLong();
		}catch(Exception e)
		{
			System.out.println("WRONG USER ID");
			return; // stop method
		}
		for(Movie movie : raptor.getUserRecommendations(userID))
			System.out.println(movie);
	}

	private void userRatings() {
		System.out.print("User ID - ");
		long userID = 0;
		try{
			userID = s.nextLong();
		}catch(Exception e)
		{
			System.out.println("WRONG USER ID");
			return; // stop method
		}
		for(Rating rating : raptor.getUserRatings(userID))
			System.out.println(rating);
	}

}
