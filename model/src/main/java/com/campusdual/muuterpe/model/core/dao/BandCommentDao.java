package com.campusdual.muuterpe.model.core.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;

@Repository("BandCommentDao")
@Lazy
@ConfigurationFile (
		configurationFile = "dao/BandCommentDao.xml",
		configurationFilePlaceholder = "dao/placeholders.properties")
public class BandCommentDao extends OntimizeJdbcDaoSupport {
	public static final String ATTR_COMMENT_ID = "comment_id";
	 public static final String ATTR_BAND_ID= "band_id" ;
	 public static final String ATTR_BODY = "comment_body" ;
	 public static final String ATTR_ALIAS = "comment_alias";
}
