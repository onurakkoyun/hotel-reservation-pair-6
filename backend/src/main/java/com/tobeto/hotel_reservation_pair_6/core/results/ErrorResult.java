package com.tobeto.hotel_reservation_pair_6.core.results;

public class ErrorResult extends Result{

	public ErrorResult() {
		super(false);
	}
	public ErrorResult(String message) {
		super(false, message);
	}
}
