package com.campusdual.muuterpe.api.core.service;


import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;


import com.ontimize.db.EntityResult;


public interface IEventService {

	public EntityResult eventQuery(Map<?, ?> keyMap, List<?> attrList);
	public EntityResult eventInsert(Map<?, ?> attrMap);
	public EntityResult eventUpdate(Map<?, ?> attrMap, Map<?, ?> keyMap);
	public EntityResult eventDelete(Map<?, ?> keyMap);
	public EntityResult nextEventsQuery(Map<String, Object> keyMap, List<String> attrList);



	

}
