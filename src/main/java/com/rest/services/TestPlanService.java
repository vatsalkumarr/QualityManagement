package com.rest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rest.dao.TestPlanDao;
import com.rest.dao.TestCaseDaoImpl;
import com.rest.dao.TestPlanDaoImpl;
import com.rest.models.TestPlan;



public class TestPlanService {

	@Autowired
	private TestPlanDao testPlanDao;
	
	public void savePlan(TestPlan testPlan) {
		testPlanDao.save(testPlan);
	}
	
	public boolean isPlanExist(TestPlan testPlan) {
		return testPlanDao.isExist(testPlan);
	}
	
	public void deletePlan(int planid) {
		testPlanDao.delete(planid);
	}
	
	public void updatePlan(TestPlan testPlan) {
		testPlanDao.update(testPlan);
	}
	
	public List<TestPlan> getPlans() {
		return ((TestPlanDaoImpl) testPlanDao).list();
	}
	
	public List<TestPlan> getPlansById(int planId) {
		return ((TestPlanDaoImpl) testPlanDao).planById(planId);
	}
	
	
}
