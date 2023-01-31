package com.spring.dal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.spring.entities.Movie;
import com.spring.serialization.Serialization;
@Component("MovieFileDao")
public class MovieFileDao implements MoviesDao
{
	private String fileName = "C:\\Users\\User\\eclipse-web-workspace\\Cinema\\src\\movies.dat";
	private Serialization<Movie> Serialzator;
	public MovieFileDao()
	{
		Serialzator = new Serialization<Movie>();
	}
	@Override
	public List<Movie> getAll() throws Exception
	{
		ArrayList<Movie> list = Serialzator.deserialize(fileName);
		Collections.sort(list);
		return list;
	}
	@Override
	public void save(Movie movie) throws Exception
	{
		List<Movie> list = Serialzator.deserialize(fileName);
		list.add(movie);
		Serialzator.serialize(fileName, (ArrayList<Movie>) list);	
	}
	@Override
	public void update(Movie movie) throws Exception
	{
		List<Movie> list = Serialzator.deserialize(fileName);
		for (int i = 0; i < list.size(); i++)
		{
			if(list.get(i).getMovieID().equals(movie.getMovieID()))
			{
				list.remove(list.get(i));
				list.add(movie);
			}
		}
		Serialzator.serialize(fileName, (ArrayList<Movie>) list);
		
	}
	@Override
	public Movie get(String id) throws Exception
	{
		List<Movie> list = Serialzator.deserialize(fileName);
		for (int i = 0; i < list.size(); i++)
		{
			if(list.get(i).getMovieID().equals(id))
			{
				return list.get(i);
			}
		}
		
		return null;
	}
	@Override
	public void delete(String id) throws Exception
	{
		List<Movie> list = Serialzator.deserialize(fileName);
		for (int i = 0; i < list.size(); i++)
		{
			if(list.get(i).getMovieID().equals(id))
			{
				list.remove(list.get(i));
			}
		}
		Serialzator.serialize(fileName, (ArrayList<Movie>) list);
		
	}
}
