package com.fahrul.springpowermock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fahrul.springpowermock.dto.OrderRequest;
import com.fahrul.springpowermock.dto.OrderResponse;
import com.fahrul.springpowermock.service.OrderService;



@SpringBootApplication
@RestController
public class SpringPowermockApplication {
	
	@Autowired
	private OrderService service;
	
	@PostMapping("/placeOrder")
	public OrderResponse placeOrder(@RequestBody OrderRequest request){
		return service.checkoutOrder(request);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringPowermockApplication.class, args);
	}

}
