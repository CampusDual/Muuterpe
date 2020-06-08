package com.campusdual.muuterpe.ws.core.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campusdual.muuterpe.api.core.service.IBandService;
import com.ontimize.jee.server.rest.ORestController;


@RestController
@RequestMapping("/bands")
@ComponentScan(basePackageClasses={com.campusdual.muuterpe.api.core.service.IBandService.class})
public class BandRestController extends ORestController<IBandService> {

	@Autowired
	private IBandService userSrv;

	@Override
	public IBandService getService() {
		return this.userSrv;
	}


}
