package com.solusi247.reportManagementApi;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class TestClient {
	
	public static final String REST_SERVICE_URI = "http://localhost:8080/com.solusi247.reportManagementApi.controller/rest";
	
	public static void main(String args[]){
		try {

			Client client = Client.create();

			WebResource webResource = client
			   .resource(REST_SERVICE_URI+"/user/getAllUser");

			ClientResponse response = webResource.accept("application/json")
	                   .get(ClientResponse.class);

			if (response.getStatus() != 200) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
			}

			String output = response.getEntity(String.class);

			System.out.println("Output from Server .... \n");
			System.out.println(output);

		  } catch (Exception e) {

			e.printStackTrace();

		  }
	}
}
