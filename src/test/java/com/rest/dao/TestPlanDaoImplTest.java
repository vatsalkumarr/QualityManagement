package com.rest.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.rest.models.TestPlan;
import com.rest.services.MvcConfiguration;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MvcConfiguration.class)
@WebAppConfiguration
public class TestPlanDaoImplTest {

	@Autowired
	private TestPlanDao testPlanDao;

	@Test
	public void testSave() throws Exception {

		TestPlan testPlan = new TestPlan();

		testPlan.setPlanTitle("titleForJUnit");
		testPlan.setPlanDesc("test desc");
		testPlan.setPlantype("test type");
		testPlan.setPlanStatus("test status");
		System.out.println(testPlan.getPlanDesc());
		int id = testPlanDao.save(testPlan);

		Assert.assertNotNull(testPlanDao.planById(id));
		testPlanDao.delete(id);

	}
	@Test
	public void testIsExixt() throws Exception {

		TestPlan testPlan = new TestPlan();

		testPlan.setPlanTitle("titleForJUnit");
		testPlan.setPlanDesc("test desc");
		testPlan.setPlantype("test type");
		testPlan.setPlanStatus("test status");
		System.out.println(testPlan.getPlanDesc());
		int id = testPlanDao.save(testPlan);

		Assert.assertTrue(testPlanDao.isExist(testPlan));
		testPlanDao.delete(id);

	}

	@Test
	public void testDelete() throws Exception {

		TestPlan testPlan = new TestPlan();

		testPlan.setPlanTitle("titleForJUnit");
		testPlan.setPlanDesc("test desc");
		testPlan.setPlantype("test type");
		testPlan.setPlanStatus("test status");
		System.out.println(testPlan.getPlanDesc());
		int id = testPlanDao.save(testPlan);

		testPlanDao.delete(id);

		Assert.assertEquals("[]", testPlanDao.planById(id).toString());

	}

	@Test
	public void testUpdate() throws Exception {

		TestPlan testPlan = new TestPlan();

		testPlan.setPlanTitle("titleForJUnit");
		testPlan.setPlanDesc("test desc");
		testPlan.setPlantype("test type");
		testPlan.setPlanStatus("test status");
		
		int id = testPlanDao.save(testPlan);
		testPlan.setPlanDesc("new desc for JUnit");
		testPlan.setPlanId(id);
		testPlanDao.update(testPlan);
		List<TestPlan> newPlan = testPlanDao.planById(id);
		Assert.assertEquals("new desc for JUnit", newPlan.get(0).getPlanDesc());
		testPlanDao.delete(id);

	}

}
