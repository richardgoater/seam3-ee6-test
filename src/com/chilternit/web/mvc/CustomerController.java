package com.chilternit.web.mvc;

import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

import org.jboss.resteasy.annotations.Form;
import org.jboss.seam.rest.exceptions.RestRequest;
import org.jboss.seam.rest.exceptions.RestResource;
import org.jboss.seam.rest.validation.ValidateRequest;
import org.jboss.seam.rest.validation.ValidationException;
import org.jboss.solder.exception.control.CaughtException;
import org.jboss.solder.exception.control.Handles;
import org.jboss.solder.exception.control.HandlesExceptions;
import com.chilternit.model.Customer;
import com.chilternit.persistence.CustomerDao;
import com.chilternit.web.Routes;
import com.googlecode.htmleasy.View;

@Stateless
@Path(Routes.customers)
@HandlesExceptions
public class CustomerController extends ModelMapController {
	@SuppressWarnings("unused")
	@Context
	private UriInfo context;

	@EJB
	CustomerDao customerDao;
	
	public CustomerController() {

	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public View customerListView() {
		Map<String, Object> model = newModel();
		model.put("customers", customerDao.getAllCustomers());

		return new JSPModelMapView("customer.jsp", model);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@ValidateRequest
	public Customer submitCustomer(@Valid @Form Customer customer) {
		customerDao.persist(customer);
		return customer;
	}
	
	public void invalidCustomerParams(@RestRequest @Handles CaughtException<ValidationException> ex, 
			@RestResource ResponseBuilder response) {
		
		JSPModelMapView customerListView = (JSPModelMapView) customerListView();
		customerListView = new JSPModelMapView(customerListView.getPath(), 
				modelWithErrors(customerListView.getModelMap(), ex.getException().getViolations()));
		response.entity(customerListView).build();
		ex.handled();
	}
	
	@DELETE
	@Path("{id}")
	public Response removeCustomer(@PathParam("id") int id) {
		customerDao.remove(id);
		return Response.ok().build();
	}
}