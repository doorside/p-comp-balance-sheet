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

import com.d.domain.Maker;
import com.d.meta.MakerModelMeta;
import com.d.model.MakerModel;
import com.google.appengine.api.datastore.Key;
import com.sun.jersey.api.json.JSONWithPadding;

@Path("/makers")
public class MakerResource {
	/** ログクラス */
	private Logger log = Logger.getLogger(this.getClass().getName());

	@GET
	@Path("{id}")
	@Produces("application/x-javascript")
	public JSONWithPadding get(@PathParam("id") Long id,
			@QueryParam("jsoncallback") @DefaultValue("fn") String callback) {
		log.info("get:" + id);
		Key key = Datastore.createKey(MakerModel.class, id);
		MakerModel makerModel = Datastore.get(MakerModel.class, key);
		Maker maker = new Maker(makerModel);
		return new JSONWithPadding(maker, callback);
	}

	@GET
	@Produces({ "application/x-javascript" })
	public JSONWithPadding list(
			@QueryParam("jsoncallback") @DefaultValue("fn") String callback) {
		log.info("get all.");
		List<Maker> makerList = new ArrayList<Maker>();

		MakerModelMeta meta = MakerModelMeta.get();
		List<MakerModel> makerModelList = Datastore.query(meta).asList();
		if (makerModelList != null) {
			for (MakerModel makerModel : makerModelList) {
				makerList.add(new Maker(makerModel));
			}
		}
		return new JSONWithPadding(new GenericEntity<List<Maker>>(makerList) {/* ignore */
		}, callback);
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Maker createMaker(Maker maker) {
		log.info("post:" + maker.getName());
		MakerModel makerModel = new MakerModel();
		makerModel.setName(maker.getName());
		Datastore.put(makerModel);
		return maker;
	}

	@PUT
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void update(@PathParam("id") Long id, Maker maker) {
		log.info("put:" + id + " " + maker.getName());
		Key key = Datastore.createKey(MakerModel.class, id);
		MakerModel makerModel = Datastore.get(MakerModel.class, key);
		makerModel.setName(maker.getName());
		Datastore.put(makerModel);
	}

	@DELETE
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void delete(@PathParam("id") Long id) {
		log.info("delete:" + id);
		Datastore.delete(Datastore.createKey(MakerModel.class, id));
	}
}
