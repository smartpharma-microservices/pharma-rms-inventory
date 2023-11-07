package com.pharma.inventory.exception;

@SuppressWarnings({ "serial" })
public class RetailerProductNotFound extends RuntimeException {
	public RetailerProductNotFound(String message) {
		super(message);
	}

	/*
	 * @Override public HttpHeaders getResponseHeaders() { return response headers }
	 */
}
