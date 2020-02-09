package com.dataStore.exception;

@SuppressWarnings("serial")
public class IllegalPathException extends Exception
{
	public IllegalPathException(String message) {
		this.message = message;
	}

	public IllegalPathException() {

	}

	String message = "Incorrect Path";

}
