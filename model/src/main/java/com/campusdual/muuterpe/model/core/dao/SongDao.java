package com.campusdual.muuterpe.model.core.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;

@Repository("SongDao")
@Lazy
@ConfigurationFile(configurationFile = "dao/SongDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")
public class SongDao extends OntimizeJdbcDaoSupport {
	public static final String ATTR_SONG_ID = "song_id";
	public static final String ATTR_ALBUM_NAME = "album_name";
	public static final String ATTR_SONG_NAME = "song_name";
	public static final String ATTR_SONG_AUDIO = "song_audio";
	
}