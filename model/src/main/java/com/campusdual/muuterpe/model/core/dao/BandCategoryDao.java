package com.campusdual.muuterpe.model.core.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;

@Repository("BandCategoryDao")
@Lazy
@ConfigurationFile(configurationFile = "dao/BandCategoryDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")
public class BandCategoryDao extends OntimizeJdbcDaoSupport {
	public static final String ATTR_BAND_ID = "band_category_id";
	public static final String ATTR_BAND_SONG_ID = "band_song_id";
	public static final String ATTR_CATEGORY_ID = "category_id";
}
