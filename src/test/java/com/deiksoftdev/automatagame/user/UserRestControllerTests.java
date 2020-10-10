package com.deiksoftdev.automatagame.user;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserRestControllerTests {

	@LocalServerPort
    int port;
	
	String api_root;
	
	@Before
    public void setup() {
        RestAssured.port = port;
        api_root = "http://localhost:"+port+UserRestController.USER_API;
    }
	
	private User createUser(String name, String email) {
		User u = new User();
		u.setName(name);
		u.setEmail(email);
		return u;
	}
	
	@Test
	public void givenNoUserById_whenGetById_thenNotFound() {
		// Given
		long nonExistingUserId = 999;
		// When
		Response response = RestAssured.get(api_root + "/" + nonExistingUserId);
		// Then
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
	}
	
	@Test
	public void givenValidUserData_whenCreate_thenUserGetsCreated() {
		// Given
		User u = createUser("a", "a@a.com");
		// When
		Response response = RestAssured.given()
		      .contentType(MediaType.APPLICATION_JSON_VALUE)
		      .body(u)
		      .post(api_root);
		// Then
		assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
		assertEquals(u.getName(), 
				response.jsonPath().get("name"));
		assertEquals(u.getEmail(), 
				response.jsonPath().get("email"));
	}
	
	@Test
	public void givenInvalidUserData_whenCreate_thenBadRequest() {
		// Given
		User u = createUser("bad", "bad@a.com");
		u.setName(null);
		// When
		Response response = RestAssured.given()
		      .contentType(MediaType.APPLICATION_JSON_VALUE)
		      .body(u)
		      .post(api_root);
		// Then
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());
	}

}
