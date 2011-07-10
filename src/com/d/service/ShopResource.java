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

import com.d.domain.Shop;
import com.d.meta.ShopModelMeta;
import com.d.model.ShopModel;
import com.google.appengine.api.datastore.Key;
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
		Key key = Datastore.createKey(ShopModel.class, id);
		ShopModel shopModel = Datastore.get(ShopModel.class, key);
		Shop shop = new Shop(shopModel);
		return new JSONWithPadding(shop, callback);
	}

	@GET
	@Produces({ "application/x-javascript" })
	public JSONWithPadding list(
			@QueryParam("jsoncallback") @DefaultValue("fn") String callback) {
		log.info("get all.");
		List<Shop> shopList = new ArrayList<Shop>();

		ShopModelMeta meta = ShopModelMeta.get();
		List<ShopModel> shopModelList = Datastore.query(meta).asList();
		if (shopModelList != null) {
			for (ShopModel shopModel : shopModelList) {
				shopList.add(new Shop(shopModel));
			}
		}
		return new JSONWithPadding(new GenericEntity<List<Shop>>(shopList) {/* ignore */
		}, callback);
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Shop createShop(Shop shop) {
		log.info("post:" + shop.getName());
		ShopModel shopModel = new ShopModel();
		shopModel.setConversionRatio(shop.getConversionRatio());
		shopModel.setName(shop.getName());
		Datastore.put(shopModel);
		return shop;
	}

	@PUT
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void update(@PathParam("id") Long id, Shop shop) {
		log.info("put:" + id + " " + shop.getName());
		Key key = Datastore.createKey(ShopModel.class, id);
		ShopModel shopModel = Datastore.get(ShopModel.class, key);
		shopModel.setName(shop.getName());
		Datastore.put(shopModel);
	}

	@DELETE
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void delete(@PathParam("id") Long id) {
		log.info("delete:" + id);
		Datastore.delete(Datastore.createKey(ShopModel.class, id));
	}
}