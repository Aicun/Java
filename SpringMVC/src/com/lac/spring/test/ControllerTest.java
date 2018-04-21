package com.lac.spring.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceView;

import com.lac.spring.controller.HomeController;
import com.lac.spring.controller.SpittleController;
import com.lac.spring.dao.SpittleRepository;
import com.lac.spring.po.Spittle;

public class ControllerTest {

	@Test
	public void testHomePage() throws Exception {
		HomeController controller = new HomeController();
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(
				MockMvcResultMatchers.view().name("index"));
	}

	@Test
	public void shouldShowRecentSpittles() throws Exception {
		List<Spittle> expectedSpittles = createSpittleList(20);
		SpittleRepository mockRepository = Mockito.mock(SpittleRepository.class);
		Mockito.when(mockRepository.findSpittles(Long.MAX_VALUE, 20)).thenReturn(
				expectedSpittles);
		SpittleController controller = new SpittleController(mockRepository);
		// SpittleController controller = new SpittleController(mockRepository);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).setSingleView(
				new InternalResourceView("/WEB-INF/views/spittles.jsp"))
				.build();
		mockMvc.perform(MockMvcRequestBuilders.get("/spittles?max=10&count=10"))
				.andExpect(MockMvcResultMatchers.view().name("spittles"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("spittleList"))
				.andExpect(
						MockMvcResultMatchers.model().attribute(
								"spittleList",CoreMatchers.hasItems(expectedSpittles.toArray())));
	}

	private List<Spittle> createSpittleList(int count) {
		List<Spittle> spittles = new ArrayList<Spittle>();
		for (int i = 0; i < count; i++) {
			spittles.add(new Spittle("Spittle " + i,new Date()));
		}
		return spittles;
	}
	
	@Test
	public void testSpittle() throws Exception {
		Spittle expectedSpittle = new Spittle("Hello",new Date());
		SpittleRepository mockRepository = Mockito.mock(SpittleRepository.class);
		Mockito.when(mockRepository.findOne(12345)).thenReturn(expectedSpittle);
		SpittleController controller = new SpittleController(mockRepository);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		mockMvc.perform(MockMvcRequestBuilders.get("/spittles/12345"))
			.andExpect(MockMvcResultMatchers.view().name("spittle"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("spittle"))
			.andExpect(MockMvcResultMatchers.model().attribute("spittle", expectedSpittle));
	}
	
	@Test
	public void shouldShowRegistration() throws Exception {
		SpittleRepository mockRepository = Mockito.mock(SpittleRepository.class);
		SpittleController controller = new SpittleController(mockRepository);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		mockMvc.perform(MockMvcRequestBuilders.get("/spittles/register"))
			.andExpect(MockMvcResultMatchers.view().name("registerForm"));
	}
	
	@Test
	public void shouldProcessRegistration() throws Exception{
		SpittleRepository mockRepository = Mockito.mock(SpittleRepository.class);
		Date d = new Date();
		Spittle unsaved = new Spittle("1",d,"1111","11111");
		Spittle saved =   new Spittle(24, "1",d,"1111","11111");
		Mockito.when(mockRepository.save(unsaved)).thenReturn(saved);
		SpittleController controller =	new SpittleController(mockRepository);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		mockMvc.perform(MockMvcRequestBuilders.post("/spittles/register")
				//.param("message","")
				.param("time", d.toString())
				.param("latitude", "1111")
				.param("longitude", "11111"))
				.andExpect(MockMvcResultMatchers.redirectedUrl("/spittles/24"));
		//Mockito.verify(mockRepository, Mockito.atLeastOnce()).save(unsaved);		
	} 
}
