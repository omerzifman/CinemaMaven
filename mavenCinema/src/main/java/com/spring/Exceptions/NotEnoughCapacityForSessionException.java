package com.spring.Exceptions;

public class NotEnoughCapacityForSessionException extends RuntimeException
{
	public NotEnoughCapacityForSessionException(String message)
	{
		super(message);
	}
}