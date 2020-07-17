package com.campusdual.muuterpe.model.core.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.campusdual.muuterpe.api.core.service.IEventService;
import com.campusdual.muuterpe.model.core.dao.BandDao;
import com.campusdual.muuterpe.model.core.dao.EventDao;
import com.ontimize.db.EntityResult;
import com.ontimize.db.SQLStatementBuilder;
import com.ontimize.db.SQLStatementBuilder.BasicExpression;
import com.ontimize.db.SQLStatementBuilder.BasicField;
import com.ontimize.db.SQLStatementBuilder.BasicOperator;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;

@Service("EventService")
@Lazy
public class EventService implements IEventService {

	@Autowired
	private EventDao eventDao;

	@Autowired
	private DefaultOntimizeDaoHelper daoHelper;

	public void loginQuery(Map<?, ?> key, List<?> attr) {
	}

	public EntityResult eventQuery(Map<?, ?> keyMap, List<?> attrList) {
		return this.daoHelper.query(eventDao, keyMap, attrList);
	}
	
	@Override
	public EntityResult eventByIdQuery(Map<String, Object> body) {
		Object filter = body.get("filter");
		Integer event_id= (Integer) ((Map<?,?>) filter).get("event_id");
		Map<String, Object> keyMap= new HashMap<String, Object>();
		keyMap.put(EventDao.ATTR_EVENT_ID, event_id);
		return this.daoHelper.query(eventDao, (Map<?, ?>) keyMap, Arrays.asList(EventDao.ATTR_EVENT_REGION,EventDao.ATTR_EVENT_ID, EventDao.ATTR_EVENT_NAME, EventDao.ATTR_EVENT_DATE_TIME ,BandDao.ATTR_NAME), "get_events_details");
	}
	
	@Override
	public EntityResult eventByStateQuery (String stateName) {
		Map<String, Object> keyMap= new HashMap<String, Object>();
		keyMap.put(EventDao.ATTR_EVENT_REGION, stateName);
		return this.daoHelper.query(eventDao, keyMap, Arrays.asList(EventDao.ATTR_EVENT_REGION,EventDao.ATTR_EVENT_NAME,EventDao.ATTR_EVENT_DATE_TIME ,"BAND"), "get_events_details");
	}
	
	@Override
	public EntityResult eventByDateQuery (String eventDate){
		Date date = new Date();
		try {
			date = stringToDate(eventDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> keyMap= new HashMap<String, Object>();
		keyMap.put(EventDao.ATTR_EVENT_DATE_TIME, date);
		return this.daoHelper.query(eventDao, keyMap, Arrays.asList(EventDao.ATTR_EVENT_REGION,EventDao.ATTR_EVENT_NAME,EventDao.ATTR_EVENT_DATE_TIME ,BandDao.ATTR_NAME), "get_events_details");
	}
	
	private Date stringToDate(String eventDate)throws Exception {
	    Date date=new SimpleDateFormat("dd/MM/yyyy").parse(eventDate);  
	    return date;  
	}
	
	public EntityResult eventInsert(Map<?, ?> attrMap) {
		return this.daoHelper.insert(eventDao, attrMap);
	}

	public EntityResult eventUpdate(Map<?, ?> attrMap, Map<?, ?> keyMap) {
		return this.daoHelper.update(eventDao, attrMap, keyMap);
	}

	public EntityResult eventDelete(Map<?, ?> keyMap) {
		Map<Object, Object> attrMap = new HashMap<>();
		attrMap.put("user_down_date", new Timestamp(Calendar.getInstance().getTimeInMillis()));
		return this.daoHelper.update(this.eventDao, attrMap, keyMap);
	}
	@Override
	public EntityResult nextEventsQuery() {
		Map<String, Object> keyMap = new HashMap<String, Object>();
		List<String> attrList = new ArrayList<String>();
		attrList.addAll(Arrays.asList(EventDao.ATTR_EVENT_NAME, EventDao.ATTR_EVENT_DATE_TIME, EventDao.ATTR_EVENT_ID, EventDao.ATTR_EVENT_REGION, EventDao.ATTR_EVENT_BUY_TICKETS));
		keyMap.put(SQLStatementBuilder.ExtendedSQLConditionValuesProcessor.EXPRESSION_KEY, this.getEventBetweenDates());
		
//		Date bbddDate= new Date();
//		LocalDate ldate=bbddDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//		long daysBetween=Duration.between(LocalDate.now().atStartOfDay(), ldate.atStartOfDay()).toDays();

//        Date bbddDate= new Date();
//        LocalDate ldate=bbddDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//        long daysBetween=Duration.between(LocalDate.now().atStartOfDay(), ldate.atStartOfDay()).toDays();

		return this.eventQuery(keyMap, attrList);
	}

	private BasicExpression getEventBetweenDates() {
		Calendar cal = Calendar.getInstance();
		Date startDate = cal.getTime();
		cal.add(Calendar.DAY_OF_WEEK, 7);
		Date endDate = cal.getTime();

		BasicField field = new BasicField(EventDao.ATTR_EVENT_DATE_TIME);
		BasicExpression bexp1 = new BasicExpression(field, BasicOperator.MORE_EQUAL_OP, startDate);
		BasicExpression bexp2 = new BasicExpression(field, BasicOperator.LESS_OP, endDate);
		return new BasicExpression(bexp1, BasicOperator.AND_OP, bexp2);
	}


}
