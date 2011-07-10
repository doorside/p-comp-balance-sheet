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

import com.d.domain.Report;
import com.d.meta.ReportModelMeta;
import com.d.model.ReportModel;
import com.google.appengine.api.datastore.Key;
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
		Key key = Datastore.createKey(ReportModel.class, id);
		ReportModel reportModel = Datastore.get(ReportModel.class, key);
		Report report = new Report(reportModel);
		return new JSONWithPadding(report, callback);
	}

	@GET
	@Produces({ "application/x-javascript" })
	public JSONWithPadding list(
			@QueryParam("jsoncallback") @DefaultValue("fn") String callback) {
		log.info("get all.");
		List<Report> reportList = new ArrayList<Report>();

		ReportModelMeta meta = ReportModelMeta.get();
		List<ReportModel> reportModelList = Datastore.query(meta).asList();
		if (reportModelList != null) {
			for (ReportModel reportModel : reportModelList) {
				reportList.add(new Report(reportModel));
			}
		}
		return new JSONWithPadding(new GenericEntity<List<Report>>(reportList) {/* ignore */
		}, callback);
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Report createReport(Report report) {
		log.info("post:" + report.getDate());
		ReportModel reportModel = new ReportModel();
		reportModel.setDate(report.getDate());
		reportModel.setShopId(report.getShopId());
		reportModel.setShopName(report.getShopName());
		reportModel.setModelId(report.getModelId());
		reportModel.setModelName(report.getModelName());
		reportModel.setInvestment(report.getInvestment());
		reportModel.setRealization(report.getRealization());
		reportModel.setNumOfPeople(report.getNumOfPeople());
		Datastore.put(reportModel);
		return report;
	}

	@PUT
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void update(@PathParam("id") Long id, Report report) {
		log.info("put:" + id + " " + report.getDate());
		Key key = Datastore.createKey(ReportModel.class, id);
		ReportModel reportModel = Datastore.get(ReportModel.class, key);
		reportModel.setDate(report.getDate());
		reportModel.setShopId(report.getShopId());
		reportModel.setShopName(report.getShopName());
		reportModel.setModelId(report.getModelId());
		reportModel.setModelName(report.getModelName());
		reportModel.setInvestment(report.getInvestment());
		reportModel.setRealization(report.getRealization());
		reportModel.setNumOfPeople(report.getNumOfPeople());
		Datastore.put(reportModel);
	}

	@DELETE
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void delete(@PathParam("id") Long id) {
		log.info("delete:" + id);
		Datastore.delete(Datastore.createKey(ReportModel.class, id));
	}
}