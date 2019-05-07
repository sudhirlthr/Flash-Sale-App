/**
 * 
 */
package com.pramati.sale;

import java.io.IOException;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pramati.sale.entity.Address;
import com.pramati.sale.entity.Authority;
import com.pramati.sale.entity.Category;
import com.pramati.sale.entity.Company;
import com.pramati.sale.entity.Orders;
import com.pramati.sale.entity.Products;
import com.pramati.sale.entity.Users;
import com.pramati.sale.service.AddressService;
import com.pramati.sale.service.OrderService;
import com.pramati.sale.service.ProductService;
import com.pramati.sale.service.UserService;

/**
 * @author sudhirk
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {AddressService.class,OrderService.class, ProductService.class, UserService.class, Products.class
		, Users.class, Orders.class, Authority.class, Company.class, Category.class, Address.class})
@WebAppConfiguration
public abstract class AbstractTestConfiguration {
	
	protected MockMvc mockMvc;
	
	@Autowired
	WebApplicationContext applicationContext;


	protected void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
	}
	
	protected String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
	
	protected <T> T mapFromJson(String json, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
		      ObjectMapper objectMapper = new ObjectMapper();
		      return objectMapper.readValue(json, clazz);
		   }
}
