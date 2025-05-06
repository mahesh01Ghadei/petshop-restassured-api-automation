package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateUserFromXlData {
	
	Logger logger = LogManager.getLogger(this.getClass());
	User userPayload;
	
	@Test(priority=1, dataProvider="userdata" , dataProviderClass=DataProviders.class)
	public void testCreateUser(String username, String fname, String lname, String email, String pswd, String phone) {
	
	Faker faker = new Faker();
	userPayload = new User();
	
	userPayload.setId(faker.idNumber().hashCode());
	userPayload.setUsername(username);
	userPayload.setFirstname(fname);
	userPayload.setLastname(lname);
	userPayload.setEmail(email);
	userPayload.setPassword(pswd);
	userPayload.setPhone(phone);
	
	logger.info("********** execution started **********");
	
	Response response = UserEndpoints.createUser(userPayload);
	Assert.assertEquals(response.getStatusCode(), 200);
	
	logger.info("********** Created user through xl data **********");
	
	
	Response readData = UserEndpoints.readUser(username);
	Assert.assertEquals(readData.getStatusCode(), 200);
	
	JsonPath jsonpath = readData.jsonPath();
	Assert.assertEquals(jsonpath.getString("username"),username);
	Assert.assertEquals(jsonpath.getString("email"),email);
	Assert.assertEquals(jsonpath.getString("phone"),phone);
	
	logger.info("********** Validated created user **********");
	
	}
	
	@Test(priority=2, dataProvider="Usernames" , dataProviderClass=DataProviders.class , dependsOnMethods="testCreateUser")
	public void testDeleteUser(String username) {
		
		Response response = UserEndpoints.deleteUser(username);
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("********** Delete users **********");
		logger.info("********** Execution Completed **********");
		
	}

	
}
