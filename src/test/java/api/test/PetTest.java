package api.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.PetEndpoints;
import api.payload.Pet;
import api.utilities.DataProviders;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PetTest {
	
	Pet petPayload;
	Logger logger;
	
	
	@Test(priority=1, dataProvider="petData" , dataProviderClass=DataProviders.class)
	public void testPostPet(String id, String categoryId, String categoryName, String petname, String petimageUrl, String tagId, String tagName) {
		logger = LogManager.getLogger(this.getClass());
		logger.info("********** execution started **********");
		
		petPayload = new Pet();
		
		Map<String, Object> category = new HashMap<>();
        category.put("id", Integer.parseInt(categoryId));
        category.put("name", categoryName);
		
        Map<String, Object> tag = new HashMap<>();
        tag.put("id", Integer.parseInt(tagId));
        tag.put("name", tagName);
		
		petPayload.setId(Integer.parseInt(id));
		petPayload.setCategory(category);
		petPayload.setName(petname);
		petPayload.setPhotoUrls(Arrays.asList(petimageUrl));
		petPayload.setTags(Arrays.asList(tag));
		
		Response response = PetEndpoints.addPet(petPayload);
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("********** New Pat added **********");
		
		Response readData = PetEndpoints.readPets(Integer.parseInt(id));
		Assert.assertEquals(readData.getStatusCode(), 200);
		
		JsonPath jsonpath = readData.jsonPath();
		Assert.assertEquals(jsonpath.getString("name"),petname);
		
		logger.info("********** Validated added pets **********");
		logger.info("********** Execution completed **********");
		
		
	}
	
	
}
