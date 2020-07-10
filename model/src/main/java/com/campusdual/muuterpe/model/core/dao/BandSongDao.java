
package com.campusdual.muuterpe.model.core.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;

@Repository("BandSongDao")
@Lazy
@ConfigurationFile(configurationFile = "dao/BandSongDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")
public class BandSongDao extends OntimizeJdbcDaoSupport {
	public static final String ATTR_BAND_ID = "band_id";
	public static final String ATTR_BAND_SONG_ID = "band_song_id";
	public static final String ATTR_SONG_ID = "song_id";
}
