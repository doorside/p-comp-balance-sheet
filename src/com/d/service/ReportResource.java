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
import com.d.domain.Report;
import com.sun.jersey.api.json.JSONWithPadding;

@Path("/reports")
public class ReportResource {
	/** ログクラス */
	private Logger log = Logger.getLogger(this.getClass().getName());

	@GET
	@Path("{id}")
	@Produces("application/x-javascript")
	public JSONWithPadding get(@PathParam("id") Long id,
			@QueryParam("jsoncallback") @DefaultValue("fn") String callback) {
		log.info("get:" + id);
		DAO dao = new DAO();
		Report report = dao.select(Report.class, id);
		return new JSONWithPadding(report, callback);
	}

	@GET
	@Produces({ "application/x-javascript" })
	public JSONWithPadding list(
			@QueryParam("jsoncallback") @DefaultValue("fn") String callback) {
		log.info("get all.");
		DAO dao = new DAO();
		List<Report> reportList = dao.selectAll(Report.class);
		return new JSONWithPadding(new GenericEntity<List<Report>>(reportList) {/* ignore */
		}, callback);
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Report createReport(Report report) {
		log.info("post:" + report.getDate());
		DAO dao = new DAO();
		dao.save(report);
		return report;
	}

	@PUT
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void update(@PathParam("id") Long id, Report report) {
		log.info("put:" + id + " " + report.getDate());
		DAO dao = new DAO();
		report.setId(id);
		dao.save(report);
	}

	@DELETE
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void delete(@PathParam("id") Long id) {
		log.info("delete:" + id);
		DAO dao = new DAO();
		dao.delete(Report.class, id);
	}
}