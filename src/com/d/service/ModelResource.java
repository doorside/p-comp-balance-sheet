package com.d.service;

import java.util.ArrayList;
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

import org.slim3.datastore.Datastore;

import com.d.domain.Model;
import com.d.meta.ModelModelMeta;
import com.d.model.MakerModel;
import com.d.model.ModelModel;
import com.d.model.SpecModel;
import com.google.appengine.api.datastore.Key;
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
		Key key = Datastore.createKey(ModelModel.class, id);
		ModelModel modelModel = Datastore.get(ModelModel.class, key);
		Model model = new Model();
		model.setId(modelModel.getId().getId());
		model.setName(modelModel.getName());
		if (modelModel.getMakerId() != null) {
			Key makerKey = Datastore.createKey(MakerModel.class,
					modelModel.getMakerId());
			MakerModel makerModel = Datastore.get(MakerModel.class, makerKey);
			if (makerModel != null) {
				model.setMakerId(makerModel.getId().getId());
				model.setMakerName(makerModel.getName());
			}
		}
		if (modelModel.getSpecId() != null) {
			Key specKey = Datastore.createKey(SpecModel.class,
					modelModel.getSpecId());
			SpecModel specModel = Datastore.get(SpecModel.class, specKey);
			if (specModel != null) {
				model.setSpecId(specModel.getId().getId());
				model.setSpecName(specModel.getName());
			}
		}
		return new JSONWithPadding(model, callback);
	}

	@GET
	@Produces({ "application/x-javascript" })
	public JSONWithPadding list(
			@QueryParam("jsoncallback") @DefaultValue("fn") String callback) {
		log.info("get all.");
		List<Model> modelList = new ArrayList<Model>();

		ModelModelMeta meta = ModelModelMeta.get();
		List<ModelModel> modelModelList = Datastore.query(meta).asList();
		if (modelModelList != null) {
			for (ModelModel modelModel : modelModelList) {
				Model model = new Model();
				model.setId(modelModel.getId().getId());
				model.setName(modelModel.getName());
				if (modelModel.getMakerId() != null) {
					Key makerKey = Datastore.createKey(MakerModel.class,
							modelModel.getMakerId());
					MakerModel makerModel = Datastore.get(MakerModel.class,
							makerKey);
					if (makerModel != null) {
						model.setMakerId(makerModel.getId().getId());
						model.setMakerName(makerModel.getName());
					}
				}
				if (modelModel.getSpecId() != null) {
					Key specKey = Datastore.createKey(SpecModel.class,
							modelModel.getSpecId());
					SpecModel specModel = Datastore.get(SpecModel.class,
							specKey);
					if (specModel != null) {
						model.setMakerId(specModel.getId().getId());
						model.setMakerName(specModel.getName());
					}
				}
				modelList.add(model);
			}
		}
		return new JSONWithPadding(new GenericEntity<List<Model>>(modelList) {/* ignore */
		}, callback);
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Model createModel(Model model) {
		log.info("post:" + model.getName());
		ModelModel modelModel = new ModelModel();
		modelModel.setName(model.getName());
		modelModel.setMakerId(model.getMakerId());
		modelModel.setSpecId(model.getSpecId());
		Datastore.put(modelModel);
		return model;
	}

	@PUT
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void update(@PathParam("id") Long id, Model model) {
		log.info("put:" + id + " " + model.getName());
		Key key = Datastore.createKey(ModelModel.class, id);
		ModelModel modelModel = Datastore.get(ModelModel.class, key);
		modelModel.setName(model.getName());
		Datastore.put(modelModel);
	}

	@DELETE
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void delete(@PathParam("id") Long id) {
		log.info("delete:" + id);
		Datastore.delete(Datastore.createKey(ModelModel.class, id));
	}
}
