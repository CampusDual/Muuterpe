package com.campusdual.muuterpe.ws.core.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campusdual.muuterpe.api.core.service.IEventService;
import com.ontimize.jee.server.rest.ORestController;


@RestController
@RequestMapping("/events")
@ComponentScan(basePackageClasses={com.campusdual.muuterpe.api.core.service.IEventService.class})
public class EventRestController extends ORestController<IEventService> {

	@Autowired
	private IEventService eventSrv;

	@Override
	public IEventService getService() {
		return this.eventSrv;
	}


}
