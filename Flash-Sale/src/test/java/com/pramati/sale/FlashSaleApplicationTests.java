package com.pramati.sale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.pramati.sale.entity.Category;
import com.pramati.sale.entity.Products;
import com.pramati.sale.entity.Users;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlashSaleApplicationTests extends AbstractTestConfiguration{

	@Before
	public void startupSetup() {
		setup();
	}

	@Test
	public void getAllUsersList() throws Exception {
		String uri = "/flash-sale/usersList";
	      MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
	         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      Users[] userlist = super.mapFromJson(content, Users[].class);
	      assertTrue(userlist.length > 0);
	}
	
	@Test
	public void registerUser() throws Exception {
		String uri = "/flash-sale/registerUser";
		Users users = new Users("peter", "peter", "parker", new BigInteger("7993610710"), Category.Male, "{noop}123","peter@gmail.com", true);
	    String inputJson = super.mapToJson(users);
	    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE)
	         .content(inputJson)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      assertEquals(content, "success");
	}
	
	@Test
	public void getAllProductsList() throws Exception {
		String uri = "/flash-sale/products/productList";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Products[] productlist = super.mapFromJson(content, Products[].class);
		assertTrue(productlist.length > 0);
	}
	
	@Test
	public void buyProducts() throws Exception {
		String uri = "/flash-sale/buy";
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("username", "sudhir");
		jsonObject.put("productid", 4);
		jsonObject.put("numberOfItemPurchased", "1");
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(jsonObject);
		jsonObject = new JSONObject();
		jsonObject.put("username", "sudhir");
		jsonObject.put("productid", 6);
		jsonObject.put("numberOfItemPurchased", "1");
		jsonArray.add(jsonObject);
		JSONObject outerJsonObject = new JSONObject();
		outerJsonObject.put("order", jsonArray);		

	    String inputJson = super.mapToJson(outerJsonObject);
	    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE)
	         .content(inputJson)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      assertEquals(content, "success");
	}
	
	@Test
	public void getOrderedProducts() throws Exception {
		String uri = "/flash-sale/myOrderedProducts";
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("username", "sudhir");
		String inputJson = super.mapToJson(jsonObject);
	    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE)
	         .content(inputJson)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      assertEquals(content, "success");
	}
}
