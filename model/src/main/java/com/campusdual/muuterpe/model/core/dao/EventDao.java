package com.campusdual.muuterpe.model.core.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;

@Repository("EventDao")
@Lazy
@ConfigurationFile (
		configurationFile = "dao/EventDao.xml",
		configurationFilePlaceholder = "dao/placeholders.properties")
public class EventDao extends OntimizeJdbcDaoSupport {
	public static final String ATTR_EVENT_ID = "event_id";
	 public static final String ATTR_EVENT_NAME= "event_name" ;
	 public static final String ATTR_EVENT_DATE_TIME = "event_date_time" ;
	 public static final String ATTR_EVENT_REGION = "event_region";
	 public static final String ATTR_EVENT_ADDRESS = "event_address";
	 public static final String ATTR_EVENT_BUY_TICKETS = "event_buy_tickets";
	 public static final String ATTR_EMPH = "band_emph";
	 public static final String ATTR_EVENT_CREATION_DATE= "event_creation_date";
	
	

}
