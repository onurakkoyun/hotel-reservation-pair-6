package com.tobeto.hotel_reservation_pair_6.core.results;

public class SuccessDataResult<T> extends DataResult<T>{

	public SuccessDataResult(T data, String message) {
		super(data, true, message);
	}
	
	public SuccessDataResult(T data) {
		super(data, true);
	}
	
	public SuccessDataResult(String message) {
		super(null, true, message);
	}
}
