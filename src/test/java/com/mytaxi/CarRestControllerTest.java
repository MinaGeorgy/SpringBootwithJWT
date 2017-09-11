package com.mytaxi;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.domainvalue.GeoCoordinate;
import com.mytaxi.service.car.CarService;
import com.mytaxi.service.driver.DriverService;
import com.mytaxi.util.JwtTokenUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarRestControllerTest {

	private MockMvc mvc;

	@Mock
	private CarService carService;

	@Mock
	private DriverService driverService;

	@Mock
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private WebApplicationContext context;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
	}

	@Test
	public void shouldGetUnauthorizedWithoutRole() throws Exception {

		this.mvc.perform(get("/v1/cars")).andExpect(status().isUnauthorized());
	}

	@Test
	public void shouldLoadCarsByIdSuccessfully() throws Exception {

		UserDetails userDetails = new DriverDTO("driver01", "driver01pw");
		
		when(this.jwtTokenUtil.getUsernameFromToken(any())).thenReturn("driver01");

		when(this.driverService.loadUserByUsername(any())).thenReturn(userDetails);

		this.mvc.perform(get("/v1/cars/1").header("Authorization",
				"Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkcml2ZXIwMSIsImF1ZCI6IndlYiIsImV4cCI6MT"
						+ "UwNTAyNzk0MSwiaWF0IjoxNTA0NDIzMTQxfQ.2M1kbqx78htyr9G_O4L3uq3UqwgZUa0"
						+ "vpcg6CP36gvxvE4w_9sUqfunumoB_Z8htLr2QQIIyIQwsVnh--yG1cg"))
				.andExpect(status().is2xxSuccessful());
	}
	@Test
	public void shouldLoadCarsSuccessfully() throws Exception {

		UserDetails userDetails = new DriverDTO("driver01", "driver01pw");
		
		when(this.jwtTokenUtil.getUsernameFromToken(any())).thenReturn("driver01");

		when(this.driverService.loadUserByUsername(any())).thenReturn(userDetails);

		this.mvc.perform(get("/v1/cars?engineType=GAS").header("Authorization",
				"Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkcml2ZXIwMSIsImF1ZCI6IndlYiIsImV4cCI6MT"
						+ "UwNTAyNzk0MSwiaWF0IjoxNTA0NDIzMTQxfQ.2M1kbqx78htyr9G_O4L3uq3UqwgZUa0"
						+ "vpcg6CP36gvxvE4w_9sUqfunumoB_Z8htLr2QQIIyIQwsVnh--yG1cg"))
				.andExpect(status().is2xxSuccessful());
	}
}
