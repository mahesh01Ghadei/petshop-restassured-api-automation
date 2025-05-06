package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetEndpoints {
	
	static ResourceBundle getUrl() {
		ResourceBundle routes = ResourceBundle.getBundle("routes");
		return routes;
	}
	
    public static Response addPet(Pet payload) {
    	
		String url = getUrl().getString("pet_post_url");
		Response response = given()
							.contentType(ContentType.JSON)
							.accept(ContentType.JSON)
							.body(payload)
				            .when()
				            .post(url);
		return response;
	}
	
	public static Response readPets(int petId) {
		
		String url = getUrl().getString("pet_get_url");
		Response response = given()
				            .pathParam("petId",petId)
				            .when()
				            .get(url);
		return response;
		
	}
	
	
	public static Response updatePetDetails(String petId, Pet payload) {
		
		String url = getUrl().getString("pet_update_url");
		Response response = given()
				            .pathParam("petId", petId)
				            .contentType(ContentType.JSON)
							.accept(ContentType.JSON)
							.body(payload)
				            .when()
				            .put(url);
		
		return response;
	}
	
	public static Response deletePetDetails(String petId) {
		
		String url = getUrl().getString("user_delete_url");
		Response response = given()
				            .pathParam("petId", petId)
				            .when()
				            .delete(url);
		return response;
		
	}
	
}
