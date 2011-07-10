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

import com.d.dao.DAO;
import com.d.domain.Spec;
import com.d.meta.SpecModelMeta;
import com.d.model.SpecModel;
import com.google.appengine.api.datastore.Key;
import com.sun.jersey.api.json.JSONWithPadding;

@Path("/specs")
public class SpecResource {
	/** ログクラス */
	private Logger log = Logger.getLogger(this.getClass().getName());

	@GET
	@Path("{id}")
	@Produces("application/x-javascript")
	public JSONWithPadding get(@PathParam("id") Long id,
			@QueryParam("jsoncallback") @DefaultValue("fn") String callback) {
		log.info("get:" + id);
		Key key = Datastore.createKey(SpecModel.class, id);
		SpecModel specModel = Datastore.get(SpecModel.class, key);
		Spec spec = new Spec(specModel);
		return new JSONWithPadding(spec, callback);
	}

	@GET
	@Produces({ "application/x-javascript" })
	public JSONWithPadding list(
			@QueryParam("jsoncallback") @DefaultValue("fn") String callback) {
		log.info("get all.");
		List<Spec> specList = new ArrayList<Spec>();

		SpecModelMeta meta = SpecModelMeta.get();
		List<SpecModel> specModelList = Datastore.query(meta).asList();
		if (specModelList != null) {
			for (SpecModel specModel : specModelList) {
				specList.add(new Spec(specModel));
			}
		}
		return new JSONWithPadding(new GenericEntity<List<Spec>>(specList) {/* ignore */
		}, callback);
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Spec createModelType(Spec spec) {
		log.info("post:" + spec.getName());
		SpecModel specModel = new SpecModel();
		specModel.setName(spec.getName());
		Datastore.put(specModel);
		return spec;
	}

	@PUT
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void update(@PathParam("id") Long id, Spec spec) {
		log.info("put:" + id + " " + spec.getName());
		Key key = Datastore.createKey(SpecModel.class, id);
		SpecModel specModel = Datastore.get(SpecModel.class, key);
		specModel.setName(spec.getName());
		Datastore.put(specModel);
	}

	@DELETE
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void delete(@PathParam("id") Long id) {
		log.info("delete:" + id);
		DAO dao = new DAO();
		Datastore.delete(Datastore.createKey(SpecModel.class, id));
	}
}
