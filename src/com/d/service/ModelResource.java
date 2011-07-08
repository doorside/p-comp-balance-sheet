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
import com.d.domain.Maker;
import com.d.domain.Model;
import com.d.domain.ModelType;
import com.sun.jersey.api.json.JSONWithPadding;

@Path("/models")
public class ModelResource {
	/** ログクラス */
	private Logger log = Logger.getLogger(this.getClass().getName());

	@GET
	@Path("{id}")
	@Produces("application/x-javascript")
	public JSONWithPadding get(@PathParam("id") Long id,
			@QueryParam("jsoncallback") @DefaultValue("fn") String callback) {
		log.info("get:" + id);
		DAO dao = new DAO();
		Model model = dao.select(Model.class, id);
		if (model.getMakerId() != null) {
			Maker maker = dao.select(Maker.class, model.getMakerId());
			if (maker != null) {
				model.setMakerName(maker.getName());
			}
		}
		if (model.getModelTypeId() != null) {
			ModelType modelType = dao.select(ModelType.class,
					model.getModelTypeId());
			if (modelType != null) {
				model.setModelTypeName(modelType.getName());
			}
		}
		return new JSONWithPadding(model, callback);
	}

	@GET
	@Produces({ "application/x-javascript" })
	public JSONWithPadding list(
			@QueryParam("jsoncallback") @DefaultValue("fn") String callback) {
		log.info("get all.");
		DAO dao = new DAO();
		List<Model> modelList = dao.selectAll(Model.class);
		return new JSONWithPadding(new GenericEntity<List<Model>>(modelList) {/* ignore */
		}, callback);
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Model createModel(Model model) {
		log.info("post:" + model.getName());
		DAO dao = new DAO();
		dao.save(model);
		return model;
	}

	@PUT
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void update(@PathParam("id") Long id, Model model) {
		log.info("put:" + id + " " + model.getName());
		DAO dao = new DAO();
		model.setId(id);
		dao.save(model);
	}

	@DELETE
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void delete(@PathParam("id") Long id) {
		log.info("delete:" + id);
		DAO dao = new DAO();
		dao.delete(Model.class, id);
	}
}
