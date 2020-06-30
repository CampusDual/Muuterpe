package com.campusdual.muuterpe.model.core.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;

@Repository("ConfigurationDao")
@Lazy
@ConfigurationFile (
		configurationFile = "dao/ConfigurationDao.xml",
		configurationFilePlaceholder = "dao/placeholders.properties")
public class ConfigurationDao extends OntimizeJdbcDaoSupport {
	public static final String ATTR_ID = "configuration_id";
	 public static final String ATTR_EMPH_DAYS= "emphasis_days" ;
	 public static final String ATTR_EVENT_IMG = "event_images_path" ;
	 public static final String ATTR_BAND_IMG = "band_images_path";
	 public static final String ATTR_NEWS_IMG = "news_images_path";
	 public static final String ATTR_BAND_NUMBER = "band_number";
	

}
