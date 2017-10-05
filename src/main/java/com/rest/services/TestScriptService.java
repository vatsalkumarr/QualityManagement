package com.rest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rest.dao.TestScriptDao;
import com.rest.dao.TestScriptDaoImpl;
import com.rest.models.TestScript;

public class TestScriptService {
	
	@Autowired
	private TestScriptDao testScriptDao;
	
	public void saveScript(TestScript testScript) {
		testScriptDao.save(testScript);
	}
	
	public boolean isScriptExist(TestScript testScript) {
		return testScriptDao.isExist(testScript);
	}
	
	public void deleteScript(int scriptid) {
		testScriptDao.delete(scriptid);
	}
	
	public void updateScript(TestScript testScript) {
		testScriptDao.update(testScript);
	}
	
	public List<TestScript> getScript() {
		return ((TestScriptDaoImpl) testScriptDao).list();
	}
	
	public List<TestScript> getScriptById(int scriptId) {
		return ((TestScriptDaoImpl) testScriptDao).scriptById(scriptId);
	}
	public List<TestScript> getScriptByCaseId(int caseId) {
		return ((TestScriptDaoImpl) testScriptDao).scriptByCaseId(caseId);
	}

	public void deleteDataSet(int dsid) {
		testScriptDao.deleteds(dsid);
		
	}

}
