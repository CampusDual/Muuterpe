package com.campusdual.muuterpe.model.core.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campusdual.muuterpe.api.core.service.IBandService;
import com.campusdual.muuterpe.model.core.dao.BandCommentDao;
import com.campusdual.muuterpe.model.core.dao.BandDao;
import com.campusdual.muuterpe.model.core.dao.BandVisitsDao;
import com.campusdual.muuterpe.model.core.dao.CategoryDao;
import com.campusdual.muuterpe.model.core.dao.ConfigurationDao;
import com.campusdual.muuterpe.model.core.dao.SongDao;
import com.ontimize.db.EntityResult;
import com.ontimize.db.SQLStatementBuilder;
import com.ontimize.db.SQLStatementBuilder.BasicExpression;
import com.ontimize.db.SQLStatementBuilder.BasicField;
import com.ontimize.db.SQLStatementBuilder.BasicOperator;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;

@Service("BandService")
@Lazy
public class BandService implements IBandService {

	@Autowired
	private BandDao bandDao;
	@Autowired
	private ConfigurationDao configurationDao;
	@Autowired
	private DefaultOntimizeDaoHelper daoHelper;
	@Autowired
	private BandCommentDao bandCommentDao;
	@Autowired
	private BandVisitsDao bandVisitsDao;

	@Override
	public EntityResult bandQuery(Map<String, Object> keyMap, List<String> attrList)
			throws OntimizeJEERuntimeException {
		return this.daoHelper.query(bandDao, keyMap, attrList);
	}

	@Override
	public EntityResult bandByCategoryQuery(Integer categoryId) {
		Map<String, Object> keyMap = new HashMap<String, Object>();
		keyMap.put(CategoryDao.ATTR_ID, categoryId);
		return this.daoHelper.query(bandDao, keyMap, Arrays.asList(BandDao.ATTR_NAME, CategoryDao.ATTR_NAME),
				"band_category");
	}

	@Override
	public EntityResult bandAndCategoryQuery() {
		Map<String, Object> keyMap = new HashMap<String, Object>();
		return this.daoHelper.query(bandDao, keyMap,
				Arrays.asList(BandDao.ATTR_ID, BandDao.ATTR_NAME, "CATEGORY", BandDao.ATTR_INFO), "band_category");
	}

	@Override
	public EntityResult bandByNameQuery(String bandName) {
		Map<String, Object> keyMap = new HashMap<String, Object>();
		keyMap.put(BandDao.ATTR_NAME, bandName);
		return this.daoHelper.query(bandDao, keyMap, Arrays.asList(BandDao.ATTR_NAME, CategoryDao.ATTR_NAME),
				"get_band_by_name");
	}

	@Override
	public EntityResult bandCommentsQuery(Map<String, Object> body) {
		Object filter = body.get("filter");
		Integer band_id = (Integer) ((Map<?, ?>) filter).get("band_id");
		Map<String, Object> keyMap = new HashMap<String, Object>();
		keyMap.put(BandDao.ATTR_ID, band_id);
		return this.daoHelper.query(bandDao, keyMap,
				Arrays.asList(BandDao.ATTR_NAME, BandCommentDao.ATTR_ALIAS, BandCommentDao.ATTR_BODY),
				"get_band_comment");
	}
	
	@Override
	public EntityResult commentInsert(Map<String, Object> body){
	return this.daoHelper.insert(bandCommentDao, body);
	}

	@Override
	public EntityResult bandSongsQuery(Map<String, Object> body) {
		Object filter = body.get("filter");
		Integer band_id = (Integer) ((Map<?, ?>) filter).get("band_id");
		Map<String, Object> keyMap = new HashMap<String, Object>();
		keyMap.put(BandDao.ATTR_ID, band_id);
		return this.daoHelper.query(bandDao, keyMap, Arrays.asList(SongDao.ATTR_SONG_AUDIO,SongDao.ATTR_SONG_NAME, SongDao.ATTR_ALBUM_NAME), "get_band_song");
	}

	@Override
	public EntityResult bandInsert(Map<String, Object> attrMap){
		return this.daoHelper.insert(bandDao, attrMap);
	}

	@Override
	public EntityResult bandUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap)
			throws OntimizeJEERuntimeException {
		return this.daoHelper.update(bandDao, attrMap, keyMap);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public EntityResult bandVisitsUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap)
			throws OntimizeJEERuntimeException {
		List<String> attr = new ArrayList<String>();
		attr.add(BandVisitsDao.ATTR_ID);
		attr.add(BandVisitsDao.ATTR_VISITS_NUM);
		EntityResult query = this.daoHelper.query(this.bandVisitsDao, keyMap, attr);
		if (query.getCode() != EntityResult.OPERATION_WRONG) {
			Hashtable visit_id;
			if (query.calculateRecordNumber() > 0) {
				 visit_id = query.getRecordValues(0);
			}else {
				keyMap.put(BandVisitsDao.ATTR_VISITS_NUM, 0);
				 EntityResult insert = this.daoHelper.insert(this.bandVisitsDao, keyMap);
				 visit_id = new Hashtable<String,Object>();
				 visit_id.put(BandVisitsDao.ATTR_ID, insert.get(BandVisitsDao.ATTR_ID));
			}
			attrMap.clear();
			attrMap.put(BandVisitsDao.ATTR_VISITS_NUM, (int)visit_id.remove(BandVisitsDao.ATTR_VISITS_NUM) + 1);
			return this.daoHelper.update(bandVisitsDao, attrMap, visit_id);
		}
		return null;
	}
	
	@Override
	public EntityResult bandDelete(Map<String, Object> keyMap) {
		return this.daoHelper.delete(this.bandDao, keyMap);
	}

	@Override
	public EntityResult bandsRecent() {
		Map<String, Object> keyMap = new HashMap<String, Object>();
		keyMap.put(SQLStatementBuilder.ExtendedSQLConditionValuesProcessor.EXPRESSION_KEY, this.getBandsRencent());
		return this.daoHelper.query(bandDao, keyMap, Arrays.asList(BandDao.ATTR_ID, BandDao.ATTR_NAME,BandDao.ATTR_INFO,BandDao.ATTR_FACEBOOK,BandDao.ATTR_INSTAGRAM,BandDao.ATTR_YOUTUBE, "CATEGORY"),
				"band_category");
	}

	private BasicExpression getBandsRencent() {
		Integer lastDays = this.getLastDaysFromConfiguration();

		Calendar configurationDays = Calendar.getInstance();
		configurationDays.add(Calendar.DAY_OF_WEEK, -lastDays);

		Calendar today = Calendar.getInstance();

		BasicField startEmphDate = new BasicField(BandDao.ATTR_CREATION_DATE);
		BasicExpression bexp1 = new BasicExpression(startEmphDate, BasicOperator.MORE_EQUAL_OP,
				configurationDays.getTime());
		BasicExpression bexp2 = new BasicExpression(startEmphDate, BasicOperator.LESS_EQUAL_OP, today.getTime());
		return new BasicExpression(bexp1, BasicOperator.AND_OP, bexp2);

	}

	private Integer getLastDaysFromConfiguration() {
		EntityResult res = this.daoHelper.query(this.configurationDao, new HashMap<String, Object>(),
				Arrays.asList(ConfigurationDao.ATTR_EMPH_DAYS));
		return res.containsKey(ConfigurationDao.ATTR_EMPH_DAYS)
				? (Integer) res.getRecordValues(0).get(ConfigurationDao.ATTR_EMPH_DAYS)
				: 0;
	}

	@Override
	public EntityResult bandVisitById(Map<String, Object> body) {
		Object filter = body.get("filter");
		Integer band_id = (Integer) ((Map<?, ?>) filter).get("band_id");
		Map<String, Object> keyMap = new HashMap<String, Object>();
		keyMap.put(BandDao.ATTR_ID, band_id);
		return this.daoHelper.query(bandDao, keyMap, Arrays.asList(BandDao.ATTR_ID, BandDao.ATTR_NAME,"CATEGORY"),
				"band_visits");
	}
	
	@Override
	public EntityResult bandsMostVisit() {
		Map<String, Object> keyMap = new HashMap<String, Object>();
		return this.daoHelper.query(bandDao, keyMap, Arrays.asList(BandDao.ATTR_ID, BandDao.ATTR_NAME,BandDao.ATTR_INFO,BandDao.ATTR_FACEBOOK,BandDao.ATTR_YOUTUBE,BandDao.ATTR_INSTAGRAM, "CATEGORY"),
				"band_visits");
	}

	private Integer getNumberOfBandsFromConfiguration() {
		EntityResult res = this.daoHelper.query(this.configurationDao, new HashMap<String, Object>(),
				Arrays.asList(ConfigurationDao.ATTR_BAND_NUMBER));
		return res.containsKey(ConfigurationDao.ATTR_BAND_NUMBER)
				? (Integer) res.getRecordValues(0).get(ConfigurationDao.ATTR_BAND_NUMBER)
				: 0;
	}

	@Override
	public EntityResult getBox() {
		Integer num = this.getNumberOfBandsFromConfiguration();
		EntityResult bands = this.entityBands();
		EntityResult bandsEmph = new EntityResult();
		EntityResult bandsRecent = this.bandsRecent();
		EntityResult bandsMostVisit = this.bandsMostVisit();

		bandsEmph.setCode(bands.getCode());
		bandsEmph.setColumnSQLTypes(bands.getColumnSQLTypes());
		bandsEmph.setMessage(bands.getMessage());

		for (int i = 0; i < num; i++) {
			if (!bandsRecent.getRecordValues(i).isEmpty()) {
				bandsEmph.addRecord(bandsRecent.getRecordValues(i));
			}
		}

		Integer nBandsRecents = num - bandsRecent.calculateRecordNumber();
		Integer n = num + nBandsRecents;

		for (int i = 0; i < n; i++) {
			bandsEmph.addRecord(bandsMostVisit.getRecordValues(i));
		}

		return bandsEmph;
	}

	private EntityResult entityBands() {
		Map<String, Object> keyMap = new HashMap<String, Object>();
		EntityResult bands = this.bandQuery(keyMap, Arrays.asList(BandDao.ATTR_NAME, BandDao.ATTR_ID,BandDao.ATTR_INFO));
		return bands;
	}

	public EntityResult bandImageQuery(Map<String, Object> body) {
		Map<String, Object> filter = (Map<String, Object>) body.get("filter");
		String band_name = (String) filter.get("band_name");
		StringBuilder builder = new StringBuilder();
		builder.append("%").append(band_name).append("_").append("%");
		
		BasicField band_image_path_bf = new BasicField(ConfigurationDao.ATTR_BAND_IMG);
		BasicExpression bexp = new BasicExpression(band_image_path_bf, BasicOperator.LIKE_OP, builder.toString());
		
		Map<String, Object> keyMap = new HashMap<String, Object>();
		keyMap.put(SQLStatementBuilder.ExtendedSQLConditionValuesProcessor.EXPRESSION_KEY, bexp);
		
		return this.daoHelper.query(this.configurationDao, keyMap,
				Arrays.asList(ConfigurationDao.ATTR_BAND_IMG));
		
	}

	@Override
	public void bandsVisitsUpdate(Map<String, Object> body) {
		System.out.println(body);

	}
	

}
