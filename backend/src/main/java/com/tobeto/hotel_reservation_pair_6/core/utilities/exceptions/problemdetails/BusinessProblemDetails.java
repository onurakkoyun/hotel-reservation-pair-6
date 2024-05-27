package com.tobeto.hotel_reservation_pair_6.core.utilities.exceptions.problemdetails;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessProblemDetails extends ProblemDetails{
	public BusinessProblemDetails(String detail) {
        setDetail(detail);
        setTitle("Business Rule Violation");
        setType("BusinessException");
    }
}
