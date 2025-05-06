package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UserTest {
	
	Faker faker;
	User userPayload;
	Logger logger;
	JsonPath jsonpath;
	
	@BeforeClass
	public void setup() {
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		logger = LogManager.getLogger(this.getClass());
		
	}
	
	@Test(priority = 1)
	public void testPostUser() {
		
		logger.info("********** execution started **********");
		
		Response response = UserEndpoints.createUser(userPayload);
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("********** created user **********");
	}
	
	@Test(priority = 2)
	public void testGetUser() {
		
		String usr = this.userPayload.getUsername();
		String email = this.userPayload.getEmail();
		String phone = this.userPayload.getPhone();		
		
		Response response = UserEndpoints.readUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		
		jsonpath = response.jsonPath();
		Assert.assertEquals(jsonpath.getString("username"),usr);
		Assert.assertEquals(jsonpath.getString("email"),email);
		Assert.assertEquals(jsonpath.getString("phone"),phone);
		
		logger.info("********** validated created user **********");
		
	}
	
	@Test(priority = 3)
	public void testUpadteUser() {
		
		userPayload.setUsername(faker.name().username());
		String updatedUname = this.userPayload.getUsername();
		
		Response response = UserEndpoints.updateUser(this.userPayload.getUsername(), userPayload);
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("********** updated user **********");
		
		Response updateResponse = UserEndpoints.readUser(this.userPayload.getUsername());
		Assert.assertEquals(updateResponse.getStatusCode(), 200);
		
        jsonpath = updateResponse.jsonPath();
		Assert.assertEquals(jsonpath.getString("username"),updatedUname);
		
		logger.info("********** validated updated user **********");
	}
	
	@Test(priority = 4)
	public void testdeleteUser() {
		
		Response response = UserEndpoints.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("********** successfully deleted user **********");
		logger.info("********** successfully executed **********");
	}
	
}
