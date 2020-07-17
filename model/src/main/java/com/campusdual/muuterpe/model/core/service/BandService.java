package com.campusdual.muuterpe.model.core.service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.campusdual.muuterpe.api.core.service.IBandService;
import com.campusdual.muuterpe.model.core.dao.BandCommentDao;
import com.campusdual.muuterpe.model.core.dao.BandDao;
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
	public EntityResult bandCommentsQuery(int bandId) {
		Map<String, Object> keyMap = new HashMap<String, Object>();
		keyMap.put(BandDao.ATTR_ID, bandId);
		return this.daoHelper.query(bandDao, keyMap,
				Arrays.asList(BandDao.ATTR_NAME, BandCommentDao.ATTR_ALIAS, BandCommentDao.ATTR_BODY),
				"get_band_comment");
	}

	@Override
	public EntityResult bandSongsQuery(Map<String, Object> body) {
		Object filter = body.get("filter");
		Integer band_id = (Integer) ((Map<?, ?>) filter).get("band_id");
		Map<String, Object> keyMap = new HashMap<String, Object>();
		keyMap.put(BandDao.ATTR_ID, band_id);
		return this.daoHelper.query(bandDao, keyMap, Arrays.asList(SongDao.ATTR_SONG_AUDIO), "get_band_song");
	}

	@Override
	public EntityResult bandInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
		return this.daoHelper.insert(bandDao, attrMap);
	}

	@Override
	public EntityResult bandUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap)
			throws OntimizeJEERuntimeException {
		return this.daoHelper.update(bandDao, attrMap, keyMap);
	}

	@Override
	public EntityResult bandDelete(Map<String, Object> keyMap) {
		return this.daoHelper.delete(this.bandDao, keyMap);
	}

	@Override
	public EntityResult bandsRecent() {
		Map<String, Object> keyMap = new HashMap<String, Object>();
		keyMap.put(SQLStatementBuilder.ExtendedSQLConditionValuesProcessor.EXPRESSION_KEY, this.getBandsRencent());
		return this.daoHelper.query(bandDao, keyMap, Arrays.asList(BandDao.ATTR_ID, BandDao.ATTR_NAME, "CATEGORY"),
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
	public EntityResult bandsMostVisit() {
		Map<String, Object> keyMap = new HashMap<String, Object>();
		return this.daoHelper.query(bandDao, keyMap, Arrays.asList(BandDao.ATTR_ID, BandDao.ATTR_NAME, "CATEGORY"),
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
		EntityResult bands = this.bandQuery(keyMap, Arrays.asList(BandDao.ATTR_NAME, BandDao.ATTR_ID));
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

}
