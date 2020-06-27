package com.fahrul.springpowermock.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fahrul.springpowermock.dto.OrderRequest;
import com.fahrul.springpowermock.dto.OrderResponse;
import com.fahrul.springpowermock.util.NotificationUtil;

@Service
public class OrderService {

	public OrderResponse checkoutOrder(OrderRequest order) {
		// call DAO
		int discount=addDiscount(order);
		order.setPrice(order.getPrice()-discount);
		String message = NotificationUtil.sendEmail(order.getEmailId());
		return new OrderResponse(order, message, HttpStatus.OK.value());
	}

	private int addDiscount(OrderRequest order) {
		System.out.println("called...");
		int price = order.getPrice();
		int discountAMount = 0;
		if (order.isDiscountable()) {
			if (order.getPrice() > 1000) {
				discountAMount = price * 10 / 100;
			} else {
				discountAMount = price;
			}
		} else {
			discountAMount = price;
		}
		return discountAMount;
	}
}
