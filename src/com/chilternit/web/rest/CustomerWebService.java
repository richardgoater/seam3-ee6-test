package com.chilternit.web.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.jboss.seam.rest.templating.ResponseTemplate;
import org.jboss.solder.resourceLoader.Resource;

import com.chilternit.model.Customer;
import com.chilternit.model.CustomerDocument;
import com.chilternit.persistence.CustomerDao;
import com.chilternit.web.Routes;
import com.chilternit.web.mvc.JSPModelMapView;
import com.googlecode.htmleasy.View;

@Stateless
@Path("/")
public class CustomerWebService {
	@SuppressWarnings("unused")
	@Context
	private UriInfo context;

	@EJB
	CustomerDao customerDao;
	
	public CustomerWebService() {

	}

	@GET
	@Path(Routes.customers + "." + "{format}")
	public Response allCustomersAsFormat(@PathParam(value = "format") String format) {
		List<Customer> customers = customerDao.getAllCustomers();
		
		Response response;
		
		if(format.equals("json"))
			response = Response.ok(new CustomerDocument(customers), MediaType.APPLICATION_JSON).build();
		else if(format.equals("xml"))
			response = Response.ok(new CustomerDocument(customers), MediaType.APPLICATION_XML).build();
		else
			response = Response.serverError().build();
		
		return response;
	}

	@GET
	@Path(Routes.upload + Routes.customers)
	@Produces("text/html")
	public View customerUploadView() {
		return new JSPModelMapView("uploadCustomers.jsp");
	}
	
	@POST
	@Path(Routes.upload + Routes.customers)
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response uploadCustomersByXML(CustomerDocument customerDocument) {
		for (Customer c : customerDocument.asList()) {
			customerDao.persistOrMerge(c);
		}			
		return allCustomersAsFormat("xml");
	}

}