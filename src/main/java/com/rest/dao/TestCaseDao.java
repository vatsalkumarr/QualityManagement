package com.rest.dao;

import java.util.List;

import com.rest.models.TestCase;

public interface TestCaseDao {

public int save(TestCase testCase);
	
	public void update(TestCase testPlan);

	public void delete(int caseid);
	
	boolean isExist(TestCase testCase);
	
	public List<TestCase> list();
	
	public List<TestCase> caseById(int caseId);
	
	public List<TestCase> caseByPlanId(int planId);
}
