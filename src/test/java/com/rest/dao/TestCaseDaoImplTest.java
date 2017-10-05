package com.rest.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.rest.models.TestCase;
import com.rest.models.TestPlan;
import com.rest.services.MvcConfiguration;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MvcConfiguration.class)
@WebAppConfiguration
public class TestCaseDaoImplTest {

	@Autowired
	private TestCaseDao testCaseDao;
	@Autowired
	private TestPlanDao testPlanDao;
	
	
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

		Assert.assertNotNull(testCaseDao.caseById(caseid));
		testPlanDao.delete(id);

	}
	@Test
	public void caseIsExixt() throws Exception {
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

		Assert.assertTrue(testCaseDao.isExist(testCase));
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

		testCaseDao.delete(caseid);
		Assert.assertEquals("[]", testCaseDao.caseById(caseid).toString());
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
		
		TestCase testCase = new TestCase();

		testCase.setCaseTitle("titleForJUnit");
		testCase.setCaseDesc("test desc");
		testCase.setPlanId(id);
		
		int caseid = testCaseDao.save(testCase);
		testCase.setCaseDesc("new desc for JUnit");
		testCase.setCaseid(caseid);
		testCaseDao.update(testCase);
	
		List<TestCase> newCase = testCaseDao.caseById(caseid);
		Assert.assertEquals("new desc for JUnit", newCase.get(0).getCaseDesc());
		testPlanDao.delete(id);

	}

}
