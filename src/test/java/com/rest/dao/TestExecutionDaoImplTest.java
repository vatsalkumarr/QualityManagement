package com.rest.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.rest.models.DataSet;
import com.rest.models.TestCase;
import com.rest.models.TestExecution;
import com.rest.models.TestPlan;
import com.rest.models.TestScript;
import com.rest.services.MvcConfiguration;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MvcConfiguration.class)
@WebAppConfiguration
public class TestExecutionDaoImplTest {

	@Autowired
	private TestExecutionDao testExecutionDao;
	@Autowired
	private TestCaseDao testCaseDao;
	@Autowired
	private TestPlanDao testPlanDao;
	@Autowired
	private TestScriptDao testScriptDao;


	@Test
	public void testSave() throws Exception {
		
		TestPlan testPlan = new TestPlan();

		testPlan.setPlanTitle("titleForJUnit");
		testPlan.setPlanDesc("test desc");
		testPlan.setPlantype("test type");
		testPlan.setPlanStatus("test status");

		int id = testPlanDao.save(testPlan);

		Assert.assertNotNull(testPlanDao.planById(id));

		TestCase testCase = new TestCase();

		testCase.setCaseTitle("titleForJUnit");
		testCase.setCaseDesc("test desc");
		testCase.setPlanId(id);

		int caseid = testCaseDao.save(testCase);

		TestScript testScript = new TestScript();

		testScript.setScriptTitle("titleForJUnit");
		testScript.setScriptDesc("test desc");
		testScript.setScriptInst("Junit instructoins");
		testScript.setCaseId(caseid);

		DataSet dataSet = new DataSet();

		dataSet.setInput("junit input");
		dataSet.setOutput("junit output");

		List<DataSet> dataSetList = new ArrayList<DataSet>();
		dataSetList.add(dataSet);

		testScript.setDataSet(dataSetList);

		int scriptid = testScriptDao.save(testScript);

		TestExecution testExecution = new TestExecution();

		testExecution.setTestPlan("titleForJUnit");
		testExecution.setTestCase("titleForJUnit");
		testExecution.setRunOn("Junit Runon");
		testExecution.setScriptStatus("test status");
		testExecution.setResult("test result");
		testExecution.setScriptId(scriptid);
		int exeid = testExecutionDao.save(testExecution);

		Assert.assertNotNull(testPlanDao.planById(id));
		testPlanDao.delete(id);

	}
	
	@Test
	public void testDelete() throws Exception {


		TestPlan testPlan = new TestPlan();

		testPlan.setPlanTitle("titleForJUnit");
		testPlan.setPlanDesc("test desc");
		testPlan.setPlantype("test type");
		testPlan.setPlanStatus("test status");

		int id = testPlanDao.save(testPlan);

		Assert.assertNotNull(testPlanDao.planById(id));

		TestCase testCase = new TestCase();

		testCase.setCaseTitle("titleForJUnit");
		testCase.setCaseDesc("test desc");
		testCase.setPlanId(id);

		int caseid = testCaseDao.save(testCase);

		TestScript testScript = new TestScript();

		testScript.setScriptTitle("titleForJUnit");
		testScript.setScriptDesc("test desc");
		testScript.setScriptInst("Junit instructoins");
		testScript.setCaseId(caseid);

		DataSet dataSet = new DataSet();

		dataSet.setInput("junit input");
		dataSet.setOutput("junit output");

		List<DataSet> dataSetList = new ArrayList<DataSet>();
		dataSetList.add(dataSet);

		testScript.setDataSet(dataSetList);

		int scriptid = testScriptDao.save(testScript);

		TestExecution testExecution = new TestExecution();

		testExecution.setTestPlan("titleForJUnit");
		testExecution.setTestCase("titleForJUnit");
		testExecution.setRunOn("Junit Runon");
		testExecution.setScriptStatus("test status");
		testExecution.setResult("test result");
		testExecution.setScriptId(scriptid);
		int exeid = testExecutionDao.save(testExecution);
		
		testExecutionDao.delete(exeid);
		Assert.assertEquals("[]", testExecutionDao.getExecutionByScriptId(scriptid).toString());

		testPlanDao.delete(id);


	}

	
}
