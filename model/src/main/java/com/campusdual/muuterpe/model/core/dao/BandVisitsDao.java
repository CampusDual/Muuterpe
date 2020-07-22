package com.campusdual.muuterpe.model.core.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;

@Repository("BandVisitsDao")
@Lazy
@ConfigurationFile (
		configurationFile = "dao/BandVisitsDao.xml",
		configurationFilePlaceholder = "dao/placeholders.properties")
public class BandVisitsDao extends OntimizeJdbcDaoSupport {
	public static final String ATTR_ID = "visits_id";
	 public static final String ATTR_BAND_ID= "band_id" ;
	 public static final String ATTR_VISITS_NUM = "visits_num" ; 

	}
