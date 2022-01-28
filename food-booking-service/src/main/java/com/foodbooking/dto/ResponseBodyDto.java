package com.foodbooking.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

@JsonInclude(Include.NON_NULL)
@Data
public class ResponseBodyDto {

	public ResponseBodyDto(String message){
		this.responseMessage = message;
	}
	private String responseMessage;

}

