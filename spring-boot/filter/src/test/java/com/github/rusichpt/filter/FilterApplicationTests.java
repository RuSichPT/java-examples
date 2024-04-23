package com.github.rusichpt.filter;

import com.github.rusichpt.filter.controller.data.TaxiRide;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FilterApplicationTests {

	@Autowired
	TestRestTemplate template;

	@LocalServerPort
	int port;

	@Test
	void givenRequest_whenFetchTaxiFareRateCard_thanOK() {
		String URL = "http://localhost:" + port;
		TaxiRide taxiRide = new TaxiRide(true, 10L);

		String fare = template.postForObject(
				URL + "/taxifare/calculate/",
				taxiRide, String.class);

		assertThat(fare, equalTo("200"));
	}

}
