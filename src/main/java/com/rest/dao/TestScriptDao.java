package com.rest.dao;

import java.util.List;

import com.rest.models.TestScript;

public interface TestScriptDao {

public int save(TestScript testScript);
	
	public void update(TestScript testScript);

	public void delete(int scriptid);
	
	boolean isExist(TestScript testScript);
	
	public List<TestScript> list();

	public List<TestScript> scriptById(int scriptid);
	
	public List<TestScript> scriptByCaseId(int caseid);

	public void deleteds(int dsid);

}
