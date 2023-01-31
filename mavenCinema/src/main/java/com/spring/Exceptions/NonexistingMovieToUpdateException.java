package com.spring.Exceptions;

public class NonexistingMovieToUpdateException extends RuntimeException
{
	public NonexistingMovieToUpdateException(String message)
	{
		super(message);
	}
}
