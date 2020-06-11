package com.campusdual.muuterpe.ws.core.rest;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.campusdual.muuterpe.api.core.service.IEventService;
import com.ontimize.db.EntityResult;
import com.ontimize.jee.server.rest.ORestController;


@RestController
@RequestMapping("/events")
@ComponentScan(basePackageClasses={com.campusdual.muuterpe.api.core.service.IEventService.class})
public class EventRestController extends ORestController<IEventService> {

	@Autowired
	private IEventService eventService;

	@Override
	public IEventService getService() {
		return this.eventService;
	}
	
	@RequestMapping(value = "nextEvents", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public EntityResult nextEventsQuery() {
		
		return this.eventService.nextEventsQuery();
	}
	
	
	
	
}
