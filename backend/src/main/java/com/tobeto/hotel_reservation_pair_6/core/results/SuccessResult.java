package com.tobeto.hotel_reservation_pair_6.core.results;

public class SuccessResult extends Result {

	public SuccessResult() {
		super(true);
	}
	public SuccessResult(String message) {
		super(true, message);
	}
}
