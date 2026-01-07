package com.app.test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class TestOne {
	public static void main(String[] args) {
		String response = RestAssured.baseURI = "https://rahulshettyacademy.com";
		RestAssured.given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{+ \"\\\"location\\\": {\\\"lat\\\": -38.383494, \\\"lng\\\": 33.427362},\"\r\n"
						+ "    + \"\\\"accuracy\\\": 50,\"\r\n"
						+ "    + \"\\\"name\\\": \\\"Frontline house\\\",\"\r\n"
						+ "    + \"\\\"phone_number\\\": \\\"(+91) 983 893 3937\\\",\"\r\n"
						+ "    + \"\\\"address\\\": \\\"29, side layout, cohen 09\\\",\"\r\n"
						+ "    + \"\\\"types\\\": [\\\"shoe park\\\",\\\"shop\\\"],\"\r\n"
						+ "    + \"\\\"website\\\": \\\"http://google.com\\\",\"\r\n"
						+ "    + \"\\\"language\\\": \\\"French-IN\\\"\"\r\n"
						+ "    + \"}")
				.when().post("/maps/api/place/add/json").then().log().all().statusCode(200).extract().asString();
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		String place_id = js.getString("place_id");
		System.out.println(place_id);

		// put
		RestAssured.given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id", place_id)
				.header("Content-Type", "application/json")
				.body("{\r\n" + "\"place_id\":\"" 
				+ place_id + "\",r\n" + 
						"\"address\":\"70 winter walk, USA\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n" + "}")
				.when().put("/maps/api/place/update/json")
				.then().log().all().statusCode(200);

		// get
		RestAssured.given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id", place_id)
				.header("Content-Type", "application/json")
				.when().get("/maps/api/place/get/json")
				.then().log().all().statusCode(200);
	}
	

}
