package com.campusdual.muuterpe.model.core.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;

@Repository("BandDao")
@Lazy
@ConfigurationFile (
		configurationFile = "dao/BandDao.xml",
		configurationFilePlaceholder = "dao/placeholders.properties")
public class BandDao extends OntimizeJdbcDaoSupport {
	public static final String ATTR_ID = "band_id";
	 public static final String ATTR_NAME= "band_name" ;
	 public static final String ATTR_INFO = "band_info" ;
	 public static final String ATTR_INSTAGRAM = "band_instagram";
	 public static final String ATTR_FACEBOOK = "band_facebook";
	 public static final String ATTR_YOUTUBE = "band_youtube";
	 public static final String ATTR_CREATION_DATE= "band_creation_date";
	
	

}
