package com.tobeto.hotel_reservation_pair_6.core.results;

public class ErrorDataResult<T> extends DataResult<T> {

	public ErrorDataResult(T data, String message) {
		super(data, false, message);
	}
	
	public ErrorDataResult(T data) {
		super(data, false);
	}
	
	public ErrorDataResult(String message) {
		super(null, false, message);
	}
}
