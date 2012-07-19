package com.chilternit.web.rest;

import java.io.File;

import javax.ws.rs.core.MediaType;

import com.chilternit.model.CustomerDocument;
import com.chilternit.web.Routes;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class JerseyXMLClient {

	public static void main(String[] args) {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(getBaseURI());
		CustomerDocument response = service.type(MediaType.APPLICATION_XML)
								.entity(new File("customers.xml"))
								.post(CustomerDocument.class);

		System.out.println(response.asList());
	}

	private static String getBaseURI() {
		return "http://localhost:9090/ee6-test" + Routes.upload
				+ Routes.customers;
	}

}
