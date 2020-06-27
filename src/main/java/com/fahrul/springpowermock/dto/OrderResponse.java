package com.fahrul.springpowermock.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderResponse {
	
	private OrderRequest response;
	private String message;
	private int statusCode;

}
