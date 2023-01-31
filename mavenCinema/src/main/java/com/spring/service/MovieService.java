package com.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.Exceptions.AgeLimitToHighException;
import com.spring.Exceptions.NonexistingMovieIDToDeleteException;
import com.spring.Exceptions.NonexistingMovieToUpdateException;
import com.spring.Exceptions.NonexistingSessionIDToDeleteException;
import com.spring.Exceptions.NonexistingSessionToUpdateException;
import com.spring.dal.MoviesDao;
import com.spring.entities.Movie;
import com.spring.entities.Session;
import com.spring.serialization.Serialization;
@Component("MovieService")
public class MovieService
{
	private int minAgeRestriction = 18;
	@Autowired
	private MoviesDao movieHandler;
	public void setMinAgeRestriction(int minAgeRestriction)
	{
		this.minAgeRestriction = minAgeRestriction;
	}
	public List<Movie> getAll() throws Exception
	{
		return movieHandler.getAll();
	}
	public void save(Movie movie) throws Exception
	{
		if(movie.getMinAge() > minAgeRestriction)
		{
			throw new AgeLimitToHighException("The age limit "+ movie.getMinAge() + " is too high and the movie will not make profit therefore is not added to cinema");
		}
		movie.setMovieID(getAvaliableMovieId());
		movieHandler.save(movie);	
	}
	public String getAvaliableMovieId() throws Exception
	{
		List<Movie> list = getAll();
		int minimumId = 1;
		for(int i =0;i< list.size();i++)
		{
			if(list.get(i).getMovieID().equals(Integer.toString(minimumId)))
			{
				minimumId++;
				System.out.println(minimumId);
			}
		}
		return Integer.toString(minimumId);
	}
	public void update(Movie movie) throws Exception
	{
		if(!getAll().contains(movie))
		{
			throw new NonexistingMovieToUpdateException("Movie with id "+ movie.getMovieID() +" doesnt exist in the archives and therfore is noneditable" );
		}
		movieHandler.update(movie);
		
	}
	public Movie get(String id) throws Exception
	{
		return movieHandler.get(id);
	}
	public void delete(String id) throws Exception
	{
		if(get(id) == null)
		{
			throw new NonexistingMovieIDToDeleteException("Movie with id "+ id +" doesnt exist in the archives and therefore wasn't deleted");
		}
		movieHandler.delete(id);
		
	}
	@PostConstruct
	private void doStartupActions() throws Exception
	{
		/*ArrayList<Movie> a = new ArrayList<Movie>();
		Serialization<Movie> b = new Serialization<>();
		b.serialize("src\\movies.dat", a);*/
		for(Movie movie: movieHandler.getAll())
		{
			System.out.println(movie);
		}
	}
	@PreDestroy
	private void doCleanUpActions() throws Exception
	{
		for(Movie movie: movieHandler.getAll())
		{
			System.out.println(movie);
		}
	}
	
}
