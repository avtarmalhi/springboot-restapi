package com.microservice.connected;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.mock;

import com.microservice.connected.controller.RestApiController;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Unit Testing of service using JUnit
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConnectedCitiesUnitTest {

	@Autowired
    private RestApiController restApiController;
	
	private MockMvc mockMvc;
	
	@Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(restApiController).build();
    }
	 
	@Test
	public void getCitiesNotConnectedTest1() throws Exception {

		restApiController = mock(RestApiController.class);

		ResultActions result = mockMvc.perform(get("/connected?origin=Philadelphia&destination=Albany").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
		      //  .andDo(print())
		        .andExpect(status().isOk())
		        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
		String expected = "No";
		assertEquals(expected, result.andReturn().getResponse().getContentAsString());
	}
	
	@Test
	public void getCitiesConnectedTest2() throws Exception {

		restApiController = mock(RestApiController.class);

		ResultActions result = mockMvc.perform(get("/connected?origin=Philadelphia&destination=Boston").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
		        .andExpect(status().isOk())
		        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
		String expected = "Yes";
		assertEquals(expected, result.andReturn().getResponse().getContentAsString());
	}
	
	@Test
	public void getCitiesNotConnectedTest3() throws Exception {

		restApiController = mock(RestApiController.class);

		ResultActions result = mockMvc.perform(get("/connected?origin=Baltimore&destination=Seattle").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
		        .andExpect(status().isOk())
		        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
		String expected = "No";
		assertEquals(expected, result.andReturn().getResponse().getContentAsString());
	}
	
	@Test
	public void getCitiesInvalidInputTest4() throws Exception {

		restApiController = mock(RestApiController.class);

		ResultActions result = mockMvc.perform(get("/connected?origin=Baltimore&destination=").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
		        .andExpect(status().isOk())
		        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
		String expected = "Service not able to return valid result. Please check if required two cities are entered.";
		assertEquals(expected, result.andReturn().getResponse().getContentAsString());
	}
}
