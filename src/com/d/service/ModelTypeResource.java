package com.d.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;

import com.d.dao.DAO;
import com.d.domain.ModelType;
import com.sun.jersey.api.json.JSONWithPadding;

@Path("/modelTypes")
public class ModelTypeResource {
	/** ログクラス */
	private Logger log = Logger.getLogger(this.getClass().getName());

	@GET
	@Path("{id}")
	@Produces("application/x-javascript")
	public JSONWithPadding get(@PathParam("id") Long id,
			@QueryParam("jsoncallback") @DefaultValue("fn") String callback) {
		log.info("get:" + id);
		DAO dao = new DAO();
		ModelType modelType = dao.select(ModelType.class, id);
		return new JSONWithPadding(modelType, callback);
	}

	@GET
	@Produces({ "application/x-javascript" })
	public JSONWithPadding list(
			@QueryParam("jsoncallback") @DefaultValue("fn") String callback) {
		log.info("get all.");
		DAO dao = new DAO();
		List<ModelType> modelTypeList = dao.selectAll(ModelType.class);
		return new JSONWithPadding(new GenericEntity<List<ModelType>>(
				modelTypeList) {/* ignore */
		}, callback);
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public ModelType createModelType(ModelType modelType) {
		log.info("post:" + modelType.getName());
		DAO dao = new DAO();
		dao.save(modelType);
		return modelType;
	}

	@PUT
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void update(@PathParam("id") Long id, ModelType modelType) {
		log.info("put:" + id + " " + modelType.getName());
		DAO dao = new DAO();
		modelType.setId(id);
		dao.save(modelType);
	}

	@DELETE
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void delete(@PathParam("id") Long id) {
		log.info("delete:" + id);
		DAO dao = new DAO();
		dao.delete(ModelType.class, id);
	}
}
