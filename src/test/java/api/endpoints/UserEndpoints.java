package api.endpoints;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;

public class UserEndpoints {
	
	static ResourceBundle getUrl() {
		ResourceBundle routes = ResourceBundle.getBundle("routes");
		return routes;
	}
	
    public static Response createUser(User payload) {
    	
		String url = getUrl().getString("post_url");
		Response response = given()
							.contentType(ContentType.JSON)
							.accept(ContentType.JSON)
							.body(payload)
				            .when()
				            .post(url);
		
		return response;
	}
	
	public static Response readUser(String username) {
		
		String url = getUrl().getString("get_url");
		Response response = given()
				            .pathParam("username",username)
				            .when()
				            .get(url);
		return response;
		
	}
	
	
	public static Response updateUser(String username, User payload) {
		
		String url = getUrl().getString("update_url");
		Response response = given()
				            .pathParam("username", username)
				            .contentType(ContentType.JSON)
							.accept(ContentType.JSON)
							.body(payload)
				            .when()
				            .put(url);
		
		return response;
	}
	
	public static Response deleteUser(String username) {
		
		String url = getUrl().getString("delete_url");
		Response response = given()
				            .pathParam("username", username)
				            .when()
				            .delete(url);
		return response;
		
	}

	
}
