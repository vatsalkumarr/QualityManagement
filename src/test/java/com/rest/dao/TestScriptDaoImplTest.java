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
import com.rest.models.TestPlan;
import com.rest.models.TestScript;
import com.rest.services.MvcConfiguration;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MvcConfiguration.class)
@WebAppConfiguration
public class TestScriptDaoImplTest {

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

		Assert.assertNotNull(testScriptDao.scriptById(scriptid));
		testPlanDao.delete(id);

	}

	@Test
	public void scriptIsExixt() throws Exception {
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

		Assert.assertNotNull(testScriptDao.scriptById(scriptid));

		Assert.assertTrue(testScriptDao.isExist(testScript));
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

		Assert.assertNotNull(testScriptDao.scriptById(scriptid));

		testScriptDao.delete(scriptid);
		;
		Assert.assertEquals("[]", testScriptDao.scriptById(scriptid).toString());
		testPlanDao.delete(id);

	}

	@Test
	public void testUpdate() throws Exception {

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

		int datasetid = testScriptDao.scriptById(scriptid).get(0).getDataSet()
				.get(0).getDataSetId();

		dataSet.setDataSetId(datasetid);

		testScript.setScriptDesc("junit updated desciption");
		testScript.setScriptId(scriptid);
		testScriptDao.update(testScript);

		List<TestScript> newScript = testScriptDao.scriptById(scriptid);
		Assert.assertEquals("junit updated desciption", newScript.get(0)
				.getScriptDesc());
		testPlanDao.delete(id);

	}

}
