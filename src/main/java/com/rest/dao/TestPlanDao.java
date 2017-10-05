package com.rest.dao;

import java.util.List;

import com.rest.models.TestPlan;




public interface TestPlanDao {

	public int save(TestPlan testPlan);
	
	public void update(TestPlan testPlan);

	public void delete(int planid);
	
	boolean isExist(TestPlan testPlan);
	
	public List<TestPlan> list();

	
	public List<TestPlan> planById(int planid);

	
	

}


