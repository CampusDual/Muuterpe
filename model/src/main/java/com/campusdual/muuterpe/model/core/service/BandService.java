package com.campusdual.muuterpe.model.core.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.campusdual.muuterpe.api.core.service.IBandService;
import com.campusdual.muuterpe.model.core.dao.BandDao;
import com.campusdual.muuterpe.model.core.dao.BandVisitsDao;
import com.campusdual.muuterpe.model.core.dao.ConfigurationDao;
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
	private BandVisitsDao bandvisitsDao;
	// private ControllerDao controllerDao;

	@Autowired
	private DefaultOntimizeDaoHelper daoHelper;

	@Override
	public EntityResult bandQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
		return this.daoHelper.query(bandDao, keyMap, attrList);
	}
	
	@Override
	public EntityResult bandVisitsQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
		return this.daoHelper.query(bandDao,keyMap, attrList,"visits");
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
		List<String> columns = new ArrayList<String>();
		columns.addAll(Arrays.asList(BandDao.ATTR_NAME, BandDao.ATTR_ID));
		keyMap.put(SQLStatementBuilder.ExtendedSQLConditionValuesProcessor.EXPRESSION_KEY, this.getBandsRencent());
		return this.bandQuery(keyMap, columns);
	}

	private BasicExpression getBandsRencent() {
		Integer lastDays = this.getLastDaysFromConfiguration();

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_WEEK, -lastDays);

		BasicField startEmphDate = new BasicField(BandDao.ATTR_CREATION_DATE);
		return new BasicExpression(startEmphDate, BasicOperator.MORE_EQUAL_OP, cal.getTime());

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
		List<String> columns = new ArrayList<String>();
		columns.addAll(Arrays.asList(BandDao.ATTR_NAME, BandDao.ATTR_ID,BandVisitsDao.ATTR_VISITIS_NUM));
		return this.bandVisitsQuery(keyMap, columns);
	}

	
	private Integer getNumberOfBandsFromConfiguration() {
		EntityResult res = this.daoHelper.query(this.configurationDao, new HashMap<String, Object>(),
				Arrays.asList(ConfigurationDao.ATTR_BAND_NUMBER));
		return res.containsKey(ConfigurationDao.ATTR_BAND_NUMBER)
				? (Integer) res.getRecordValues(0).get(ConfigurationDao.ATTR_BAND_NUMBER)
				: 0;
	}
		

	
}
