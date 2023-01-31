package com.spring.dal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.spring.entities.Movie;
import com.spring.entities.Session;
import com.spring.serialization.Serialization;
@Component("SessionFileDao")
public class SessionFileDao implements SessionsDao
{
	private String fileName = "C:\\Users\\User\\eclipse-web-workspace\\Cinema\\src\\sessions.dat";
	private Serialization<Session> Serialzator;
	public SessionFileDao()
	{
		Serialzator = new Serialization<Session>();
	}
	@Override
	public List<Session> getAll() throws Exception
	{
		ArrayList<Session> list = Serialzator.deserialize(fileName);
		Collections.sort(list);
		return list;
	}
	@Override
	public void save(Session session) throws Exception
	{
		List<Session> list = Serialzator.deserialize(fileName);
		list.add(session);
		Serialzator.serialize(fileName, (ArrayList<Session>) list);
		
		
	}
	@Override
	public void update(Session session) throws Exception
	{
		List<Session> list = Serialzator.deserialize(fileName);
		for (int i = 0; i < list.size(); i++)
		{
			if(list.get(i).getSessionID().equals(session.getMovieID()))
			{
				list.remove(list.get(i));
				list.add(session);
			}
		}
		Serialzator.serialize(fileName, (ArrayList<Session>) list);
		
	}
	@Override
	public Session get(String id) throws Exception
	{
		List<Session> list = Serialzator.deserialize(fileName);
		for (int i = 0; i < list.size(); i++)
		{
			if(list.get(i).getSessionID().equals(id))
			{
				return list.get(i);
			}
		}
		
		return null;
	}
	@Override
	public void delete(String id) throws Exception
	{
		List<Session> list = Serialzator.deserialize(fileName);
		for (int i = 0; i < list.size(); i++)
		{
			if(list.get(i).getSessionID().equals(id))
			{
				list.remove(list.get(i));
			}
		}
		Serialzator.serialize(fileName, (ArrayList<Session>) list);
		
	}
}
