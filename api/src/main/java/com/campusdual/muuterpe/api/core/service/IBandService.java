package com.campusdual.muuterpe.api.core.service;

import java.util.List;
import java.util.Map;

import com.ontimize.db.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;

public interface IBandService {

	public EntityResult bandQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException;

	public EntityResult bandInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException;

	public EntityResult bandUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap)
			throws OntimizeJEERuntimeException;

	public EntityResult bandDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException;

	public EntityResult bandsRecent() throws OntimizeJEERuntimeException;

	public EntityResult bandsMostVisit() throws OntimizeJEERuntimeException;

	public EntityResult bandByCategoryQuery(Integer categoryId);

	public EntityResult bandByNameQuery(String bandName);

	public EntityResult getBox() throws OntimizeJEERuntimeException;

	public EntityResult bandCommentsQuery(int bandId) throws OntimizeJEERuntimeException;

	public EntityResult bandAndCategoryQuery() throws OntimizeJEERuntimeException;

    public EntityResult bandSongsQuery(int bandId) throws OntimizeJEERuntimeException;
	

	
	}
