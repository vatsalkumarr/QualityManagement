package com.rest.dao;

import java.util.List;

import com.rest.models.TestExecution;

public interface TestExecutionDao {
	
public int save(TestExecution testExecution);
	
	

	public void delete(int executionid);
	
	
	public List<TestExecution> list();
	
	public List<TestExecution> getExecutionByScriptId(int id);

	
}
