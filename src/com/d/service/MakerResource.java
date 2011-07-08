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
		DAO dao = new DAO();
		Maker maker = dao.select(Maker.class, id);
		return new JSONWithPadding(maker, callback);
	}

	@GET
	@Produces({ "application/x-javascript" })
	public JSONWithPadding list(
			@QueryParam("jsoncallback") @DefaultValue("fn") String callback) {
		log.info("get all.");
		DAO dao = new DAO();
		List<Maker> makerList = dao.selectAll(Maker.class);
		return new JSONWithPadding(new GenericEntity<List<Maker>>(makerList) {/* ignore */
		}, callback);
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Maker createMaker(Maker maker) {
		log.info("post:" + maker.getName());
		DAO dao = new DAO();
		dao.save(maker);
		return maker;
	}

	@PUT
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void update(@PathParam("id") Long id, Maker maker) {
		log.info("put:" + id + " " + maker.getName());
		DAO dao = new DAO();
		maker.setId(id);
		dao.save(maker);
	}

	@DELETE
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void delete(@PathParam("id") Long id) {
		log.info("delete:" + id);
		DAO dao = new DAO();
		dao.delete(Maker.class, id);
	}
}
