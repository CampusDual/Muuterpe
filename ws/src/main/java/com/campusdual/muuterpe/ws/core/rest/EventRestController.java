package com.campusdual.muuterpe.ws.core.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.campusdual.muuterpe.api.core.service.IEventService;
import com.ontimize.db.EntityResult;
import com.ontimize.jee.server.rest.ORestController;

@RestController
@RequestMapping("/events")
@ComponentScan(basePackageClasses = { com.campusdual.muuterpe.api.core.service.IEventService.class })
public class EventRestController extends ORestController<IEventService> {

	@Autowired
	private IEventService eventService;

	@Override
	public IEventService getService() {
		return this.eventService;
	}

	@RequestMapping(value = "/getNextEvents/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public EntityResult geNextEvents() {
		return this.eventService.nextEventsQuery();
	}
	
	@RequestMapping(value = "/getEventsById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)	
	public EntityResult getEventsById(@RequestParam(required = true) int eventId) {
		return this.eventService.eventById(eventId);
	}
	
	@RequestMapping(value = "/getEventsByState", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)	
	public EntityResult getEventsByState(@RequestParam(required = true) String stateName) {
		return this.eventService.eventByStateQuery(stateName);
	}
	
	@RequestMapping(value = "/getEventsByDate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)	
	public EntityResult getEventsByDate(@RequestParam(required = true) String eventDate) {
		return this.eventService.eventByDateQuery(eventDate);
	}

}


