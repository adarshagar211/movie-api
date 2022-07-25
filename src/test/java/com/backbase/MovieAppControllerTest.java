package com.backbase;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.backbase.controller.MovieController;
import com.backbase.service.MovieService;

@SpringBootTest( classes = {MovieController.class})
@AutoConfigureMockMvc
public class MovieAppControllerTest {
	  
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MovieService frankOlCarService;

	@Test
	public void testgetAllCarService() throws Exception {
		this.mockMvc.perform(get("/warehouse")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].warehouseId").value("1"));
	}
	
	
}