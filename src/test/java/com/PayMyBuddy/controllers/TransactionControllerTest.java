package com.PayMyBuddy.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.PayMyBuddy.dto.PaymentDTO;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)

@SpringBootTest
@AutoConfigureMockMvc
class TransactionControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	
	@Test
	public void paymentToConnectionTest() {
		double amount = Math.random();
		try {
			mockMvc.perform(post("/user/operation/payment").content(asJsonString(new PaymentDTO("test",amount)))
					.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isCreated());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

			
	}

}
