package br.com.lhs.jee6angular.rest;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.lhs.jee6angular.model.Model;
import br.com.lhs.jee6angular.service.PersistenceService;

public abstract class AbstractServiceResource<T extends Model>
		implements
			Serializable {

	private static final long serialVersionUID = 1L;

	public static boolean isResponseOk(Response response) {
		return response != null
				&& response.getStatus() == Response.Status.OK.getStatusCode();
	}

	protected Response validateModel(T model) {
		return Response.ok().build();
	}

	protected abstract PersistenceService<T> getService();

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response defaultPut(T model) {
		Response response = validateModel(model);
		if (AbstractServiceResource.isResponseOk(response))
			getService().save(model);
		return response;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response defaultPost(T model) {
		Response response = validateModel(model);
		if (AbstractServiceResource.isResponseOk(response))
			getService().save(model);
		return response;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response defaultGet() {
		List<T> all = getService().findAll();
		if (all != null)
			return Response.ok(all).build();
		return Response.status(Status.NO_CONTENT).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response defaultGet(@QueryParam("i") Integer firstResult,
			@QueryParam("m") Integer maxResults) {
		List<T> all = getService().find(firstResult, maxResults);
		if (all != null)
			return Response.ok(all).build();
		return Response.status(Status.NO_CONTENT).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response defaultGet(@PathParam("id") Long id) {
		T model = getService().findById(id);
		if (model != null)
			return Response.ok(model).build();
		return Response.status(Status.NOT_FOUND).build();
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response defaultDelete(@PathParam("id") Long id) {
		T model = getService().findById(id);
		if (model != null)
			// TODO: remove.
			return Response.ok().build();
		return Response.status(Status.NOT_FOUND).build();
	}

}
