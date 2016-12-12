package models;

public class Movie {
	
	static long counter = 01;
	private long id;
	private String title;
	private String year;
	private String url;
	
	public Movie(String title, String year,String url)
	{
		this.id=counter++;
		this.title=title;
		this.year=year;
		this.url=url;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", year=" + year + ", url=" + url + "]";
	}

	public static long getCounter() {
		return counter;
	}
	
	public static void setCounter(long counter) {
		Movie.counter = counter;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getYear() {
		return year;
	}

	public String getUrl() {
		return url;
	}
	
	
}
