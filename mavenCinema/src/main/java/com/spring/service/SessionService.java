package com.spring.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.Exceptions.NonexistingSessionIDToDeleteException;
import com.spring.Exceptions.NonexistingSessionToUpdateException;
import com.spring.Exceptions.NotEnoughCapacityForSessionException;
import com.spring.Exceptions.NotEnoughSpaceException;
import com.spring.dal.SessionFileDao;
import com.spring.entities.Movie;
import com.spring.entities.Session;
import com.spring.serialization.Serialization;
@Component("SessionService")
public class SessionService
{
	private int maxCapacityInCinema = 250;
	private int maxSessions = 50;
	private Date latestHour = new Date();
	@Autowired
	private SessionFileDao sessionHandler;
	public void setMaxCapacityInCinema(int maxCapacityInCinema)
	{
		this.maxCapacityInCinema = maxCapacityInCinema;
	}
	public void setMaxSessions(int maxSessions)
	{
		this.maxSessions = maxSessions;
	}
	public void setLatestHour(String latestHour) throws ParseException
	{
		SimpleDateFormat t1= new SimpleDateFormat("hh:mm");
		this.latestHour =  t1.parse(latestHour);
	}
	public List<Session> getAll() throws Exception
	{
		return sessionHandler.getAll();
	}
	public void save(Session session) throws Exception
	{
		if(getAll().size() == maxSessions)
		{
			throw new NotEnoughSpaceException("The max number of sessions is: "+ maxSessions);
		}
		if(session.getCapacity()>maxCapacityInCinema)
		{
			throw new NotEnoughCapacityForSessionException("There is no éhall with capacity over " +maxCapacityInCinema + " therefore this session cant be added to schedule");
		}
		session.setSessionID(getAvaliableSessionId());
		sessionHandler.save(session);
		
		
	}
	public String getAvaliableSessionId() throws Exception
	{
		List<Session> list = getAll();
		int minimumId = 1;
		for(int i =0;i< list.size();i++)
		{
			if(list.get(i).getSessionID().equals(Integer.toString(minimumId)))
			{
				minimumId++;
			}
		}
		return Integer.toString(minimumId);
	}
	public void update(Session session) throws Exception
	{
		if(!getAll().contains(session))
		{
			throw new NonexistingSessionToUpdateException("Session with id "+ session.getSessionID() +" doesnt exist in the archives and therfore is noneditable" );
		}
		sessionHandler.update(session);
		
	}
	public Session get(String id) throws Exception
	{
		return sessionHandler.get(id);
	}
	public void delete(String id) throws Exception
	{
		if(get(id) == null)
		{
			throw new NonexistingSessionIDToDeleteException("Session with id "+ id +" doesnt exist in the archives and therefore wasn't deleted");
		}
		sessionHandler.delete(id);
	}
	@PostConstruct
	private void doStartupActions() throws Exception
	{
		/*ArrayList<Session> a = new ArrayList<Session>();
		Serialization<Session> b = new Serialization<>();
		b.serialize("src\\sessions.dat", a);*/
		for(Session movie: getAll())
		{
			System.out.println(movie);
		}
	}
	@PreDestroy
	private void doCleanUpActions() throws Exception
	{
		for(Session movie: getAll())
		{
			System.out.println(movie);
		}
	}
}
