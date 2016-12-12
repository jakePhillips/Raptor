package models;

public class Rating {
	
	long userID;
	long movieID;
	int rating;
	
	public Rating(long userID,long movieID,int rating)
	{
		this.userID=userID;
		this.movieID = movieID;
		if(rating < -5 || rating > 5)
			throw new IllegalArgumentException();
		this.rating= rating;
	}

	@Override
	public String toString() {
		return "Rating [userID=" + userID + ", movieID=" + movieID + ", rating=" + rating + "]";
	}

	public long getUserID() {
		return userID;
	}

	public long getMovieID() {
		return movieID;
	}

	public int getRating() {
		return rating;
	}
}
