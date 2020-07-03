package com.campusdual.muuterpe.model.core.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;

@Repository("BandEventDao")
@Lazy
@ConfigurationFile(configurationFile = "dao/BandEventDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")
public class BandEventDao extends OntimizeJdbcDaoSupport {
	public static final String ATTR_BAND_EVENT_ID = "band_event_id";
	public static final String ATTR_BAND_ID = "band_id";
	public static final String ATTR_EVENT_ID = "event_id";
}
