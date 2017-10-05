package com.rest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rest.dao.TestExecutionDao;
import com.rest.dao.TestExecutionDaoImpl;
import com.rest.models.TestExecution;

public class TestExecutionService {
	
	@Autowired
	private TestExecutionDao testExecutionDao;
	
	public void saveTestExecution(TestExecution testExecution) {
		testExecutionDao.save(testExecution);
	}
	
	
	
	public void deleteTestExecution(int testExecutionid) {
		testExecutionDao.delete(testExecutionid);
	}
	
	
	public List<TestExecution> getExecution() {
		return ((TestExecutionDaoImpl) testExecutionDao).list();
	}

	public List<TestExecution> getExecutionByScriptId(int id) {
		return ((TestExecutionDaoImpl) testExecutionDao).getExecutionByScriptId(id);
	}



	
	
	

}
