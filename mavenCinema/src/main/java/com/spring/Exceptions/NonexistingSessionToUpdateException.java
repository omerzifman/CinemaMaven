package com.spring.Exceptions;

public class NonexistingSessionToUpdateException extends RuntimeException
{
	public NonexistingSessionToUpdateException(String message)
	{
		super(message);
	}
}
