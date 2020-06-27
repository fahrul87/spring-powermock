package com.fahrul.springpowermock;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.doReturn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


import com.fahrul.springpowermock.dto.OrderRequest;
import com.fahrul.springpowermock.dto.OrderResponse;
import com.fahrul.springpowermock.service.OrderService;
import com.fahrul.springpowermock.util.NotificationUtil;


@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "com.fahrul.springpowermock.*")
public class SpringPowermockApplicationTests {

	@InjectMocks
	private OrderService service;
	
	OrderRequest request = new OrderRequest(364,"istimiwir",1,10000,"test@gmail.com",true);
	
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(NotificationUtil.class);
	}
	
	@Test
	public void testStaticMethod() {
		String emailid = "test@gmail.com";
		PowerMockito.mockStatic(NotificationUtil.class);
		
		when(NotificationUtil.sendEmail(emailid)).thenReturn("success");
		
		OrderResponse response = service.checkoutOrder(request);
		assertEquals("success", response.getMessage());
		
		
	}
	
	@Test
	public void testPrivateMethod() throws Exception{
		
		OrderService spy = PowerMockito.spy(service);
		doReturn(2000).when(spy,"addDiscount",ArgumentMatchers.any());
		OrderResponse response = spy.checkoutOrder(request);
		int price = response.getResponse().getPrice();
		System.out.println("Price : "+ price);
		assertEquals(8000, price);
	}

}
