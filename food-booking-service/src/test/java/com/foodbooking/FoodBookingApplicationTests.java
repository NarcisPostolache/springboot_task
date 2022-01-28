package com.foodbooking;

import com.foodbooking.controller.FoodBookingController;
import com.foodbooking.dto.RequestBodyDto;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FoodBookingApplicationTests {

	@Autowired
	private FoodBookingController controller;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void contextLoads(){
		assertThat(controller).isNotNull();
	}


	@Test
	public void searchShouldReturnResults() throws Exception {
		this.mockMvc.perform(get("/searchProduct").param("searchInput", "cake")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Cheesecake")));
	}

	@Test
	public void buyShouldBeSuccessful() throws Exception {
		RequestBodyDto reqBody = new RequestBodyDto();
		reqBody.setUserId("test");
		reqBody.setProductId(1L);
		reqBody.setQuantity(0);
		reqBody.setAccountNumber(4447427245L);

		this.mockMvc.perform(put("/buyProduct").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(reqBody))).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void orderHistoryShouldReturnOk() throws Exception {
		this.mockMvc.perform(get("/getOrderHistory").param("userId", "Alex")).andDo(print()).andExpect(status().isOk());
	}
}
