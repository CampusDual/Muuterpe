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
	
//	@RequestMapping(value = "/getBandsCategory", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)	
//	public EntityResult getBandCategoy(@RequestParam(required = true) Integer id) {
//		return this.bandSrv.bandCategoryQuery(id);
//	}
}
