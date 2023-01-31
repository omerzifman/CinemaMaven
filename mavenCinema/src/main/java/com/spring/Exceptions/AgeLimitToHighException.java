package com.spring.Exceptions;

public class AgeLimitToHighException extends RuntimeException
{
	public AgeLimitToHighException(String message)
	{
		super(message);
	}
}
