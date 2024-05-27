package com.tobeto.hotel_reservation_pair_6.core.utilities.exceptions.problemdetails;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ValidationProblemDetails extends ProblemDetails{
	private List<String> errors;

    public ValidationProblemDetails(List<String> errors) {
        setDetail("One or more validation error(s)!");
        setType("ValidationException");
        setTitle("Validation Rule Violation");
        this.errors = errors;
    }
}
