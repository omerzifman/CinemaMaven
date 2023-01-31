package com.spring.Exceptions;

public class NonexistingMovieIDToDeleteException extends RuntimeException
{
	public NonexistingMovieIDToDeleteException(String message)
	{
		super(message);
	}
}
