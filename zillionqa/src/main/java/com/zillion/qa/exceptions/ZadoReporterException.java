package com.zillion.qa.exceptions;

public class ZadoReporterException
extends Exception
{
	private String message;

	public ZadoReporterException() {}

	public ZadoReporterException(String message)  {
		this.message = message;
	}

	public ZadoReporterException(String message, Throwable ex)  {
		super(message, ex);
		this.message = message;
	}
	public String toString()  {
		return "[Custom Reporter Exception] " + this.message;
	}
}
