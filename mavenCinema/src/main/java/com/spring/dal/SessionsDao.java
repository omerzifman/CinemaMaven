package com.spring.dal;

import java.util.List;

import com.spring.entities.Movie;
import com.spring.entities.Session;

public interface SessionsDao
{
	public List<Session> getAll() throws Exception;
	public void save(Session session) throws Exception;
	public void update(Session session) throws Exception;
	public void delete(String id) throws Exception;
	public Session get(String id) throws Exception;
}
