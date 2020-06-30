package com.campusdual.muuterpe.ws.core.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.campusdual.muuterpe.api.core.service.IBandService;
import com.ontimize.db.EntityResult;
import com.ontimize.jee.server.rest.ORestController;


@RestController
@RequestMapping("/bands")
@ComponentScan(basePackageClasses={com.campusdual.muuterpe.api.core.service.IBandService.class})
public class BandRestController extends ORestController<IBandService> {

	@Autowired
	private IBandService bandSrv;

	@Override
	public IBandService getService() {
		return this.bandSrv;
	}
	
	@RequestMapping(value = "/getBandsRecent", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)	
	public EntityResult getBandRecent() {
		return this.bandSrv.bandsRecent();
	}
	
	@RequestMapping(value = "/getBandsVisits", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)	
	public EntityResult getBandVisits() {
		return this.bandSrv.bandsMostVisit();
	}
	
	@RequestMapping(value = "/getBandsByCategory", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)	
	public EntityResult getBandByCategoy(@RequestParam(required = true) Integer categoryId) {
		return this.bandSrv.bandCategoryQuery(categoryId);
	}
	
	@RequestMapping(value = "/getBandsByName", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)	
	public EntityResult getBandByName(@RequestParam(required = true) String bandName) {
		return this.bandSrv.bandbyNameQuery(bandName);
	}
	
	@RequestMapping(value = "/getBandsEmph", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)	
	public EntityResult getBandEmph() {
		return this.bandSrv.getBox();
	}
}
