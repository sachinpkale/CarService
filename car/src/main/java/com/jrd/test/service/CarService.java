package com.jrd.test.service;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.jrd.test.entities.Car;
import com.jrd.test.manager.CarManager;
 
@Path("/")
public class CarService {
	
	CarManager manager;
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getCarDetails(@PathParam("id") String id) {
		Car car = manager.getCarDetails(id);
		if(car == null) {
			return Response.status(404).entity("Entity Not Found").build();
		}
		return Response.status(200).entity(car).build(); 
	}

	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCarDetails(Car car) {
		try {
			manager.addCarDetails(car);
		} catch(Exception e) {
			return Response.status(10000).entity("ERROR: " + e.getMessage()).build();
		}
		return Response.status(200).entity(car).build(); 
	}

	@PUT
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCarDetails(Car car) {
		if(car.getId() == null || manager.getCarDetails(car.getId().toString()) == null) {
			return Response.status(404).entity("Entity Not Found").build();			
		}
		try {
			car = manager.updateCarDetails(car);
		} catch(Exception e) {
			return Response.status(10001).entity("ERROR: " + e.getMessage()).build();
		}
		return Response.status(200).entity(car).build(); 
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteCarDetails(@PathParam("id") String id) {
		if(manager.getCarDetails(id) == null) {
			return Response.status(404).entity("Entity Not Found").build();			
		}
		try {
			manager.deleteCarDetails(id);
		} catch(Exception e) {
			return Response.status(10002).entity("ERROR: " + e.getMessage()).build();
		}
		return Response.status(200).entity("Success").build(); 
	}

	public CarManager getManager() {
		return manager;
	}

	public void setManager(CarManager manager) {
		this.manager = manager;
	}
}