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
import com.d.domain.Shop;
import com.sun.jersey.api.json.JSONWithPadding;

@Path("/shops")
public class ShopResource {
	/** ログクラス */
	private Logger log = Logger.getLogger(this.getClass().getName());

	@GET
	@Path("{id}")
	@Produces("application/x-javascript")
	public JSONWithPadding get(@PathParam("id") Long id,
			@QueryParam("jsoncallback") @DefaultValue("fn") String callback) {
		log.info("get:" + id);
		DAO dao = new DAO();
		Shop shop = dao.select(Shop.class, id);
		return new JSONWithPadding(shop, callback);
	}

	@GET
	@Produces({ "application/x-javascript" })
	public JSONWithPadding list(
			@QueryParam("jsoncallback") @DefaultValue("fn") String callback) {
		log.info("get all.");
		DAO dao = new DAO();
		List<Shop> shopList = dao.selectAll(Shop.class);
		return new JSONWithPadding(new GenericEntity<List<Shop>>(shopList) {/* ignore */
		}, callback);
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Shop createShop(Shop shop) {
		log.info("post:" + shop.getName());
		DAO dao = new DAO();
		dao.save(shop);
		return shop;
	}

	@PUT
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void update(@PathParam("id") Long id, Shop shop) {
		log.info("put:" + id + " " + shop.getName());
		DAO dao = new DAO();
		shop.setId(id);
		dao.save(shop);
	}

	@DELETE
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void delete(@PathParam("id") Long id) {
		log.info("delete:" + id);
		DAO dao = new DAO();
		dao.delete(Shop.class, id);
	}
}