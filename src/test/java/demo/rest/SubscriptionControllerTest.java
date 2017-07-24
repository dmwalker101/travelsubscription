package demo.rest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import demo.model.Subscription;
import demo.service.SubscriptionService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SubscriptionController.class, secure = false)
public class SubscriptionControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SubscriptionService subscriptionService;

	// Subscription mockSubscription = new Subscription();

	String exampleSubscriptionJson = "{\"subscription_id\":\"DECK345\"}";

	@Test
	public void createSubscriptionOfEvents() throws Exception {
		Subscription mockSubscription = new Subscription();
		mockSubscription.setSubscription_id("DECK345");

		Mockito.when(subscriptionService.createSubscription(Mockito.any(Subscription.class))).thenReturn(mockSubscription);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/play-subscriptionservice/v1/subscription")
				.accept(MediaType.APPLICATION_JSON).content(exampleSubscriptionJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		assertEquals("http://localhost/play-subscriptionservice/v1/subscription/DECK345", response.getHeader(HttpHeaders.LOCATION));

	}

	
	@Test
	public void retrieveEventsForSubscription() throws Exception {

		Subscription mockSubscription = new Subscription();

		List<String> events = new ArrayList<String>();
 
			events.add("116");
			events.add("117");
		 
		 
		mockSubscription.setSubscription_id("DECK345");
		mockSubscription.setEvents(events);

		Mockito.when(subscriptionService.getSubscription(Mockito.anyString())).thenReturn(mockSubscription);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/play-subscriptionservice/v1/subscription/DECK345")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{\"subscription_id\":\"DECK345\",\"events\":[\"116\",\"117\"]}";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}  
	

}
