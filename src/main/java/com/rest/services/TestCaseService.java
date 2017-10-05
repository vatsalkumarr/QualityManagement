package com.rest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rest.dao.TestCaseDao;
import com.rest.dao.TestCaseDaoImpl;
import com.rest.models.TestCase;

public class TestCaseService {

	@Autowired
	private TestCaseDao testCaseDao;
	
	public void saveCase(TestCase testCase) {
		testCaseDao.save(testCase);
	}
	
	public boolean isCaseExist(TestCase testCase) {
		return testCaseDao.isExist(testCase);
	}
	
	public void deleteCase(int caseid) {
		testCaseDao.delete(caseid);
	}
	
	public void updateCase(TestCase testCase) {
		testCaseDao.update(testCase);
	}
	
	public List<TestCase> getCase() {
		return ((TestCaseDaoImpl) testCaseDao).list();
	}
	
	public List<TestCase> getCaseById(int caseid) {
		return ((TestCaseDaoImpl) testCaseDao).caseById(caseid);
	}
	public List<TestCase> getCaseByPlanId(int planid) {
		return ((TestCaseDaoImpl) testCaseDao).caseByPlanId(planid);
	}
}
