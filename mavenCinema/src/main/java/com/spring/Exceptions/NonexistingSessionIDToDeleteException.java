package com.spring.Exceptions;

public class NonexistingSessionIDToDeleteException extends RuntimeException
{
	public NonexistingSessionIDToDeleteException(String message)
	{
		super(message);
	}
}
