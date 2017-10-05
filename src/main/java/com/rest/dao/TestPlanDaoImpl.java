package com.rest.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.rest.models.TestPlan;


public class TestPlanDaoImpl implements TestPlanDao {

	private JdbcTemplate jdbcTemplate;

	public TestPlanDaoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int save(TestPlan testPlan) {
		
		String sql = "insert into plan (title,description,status,type) values(?,?,?,?)";

		jdbcTemplate.update(sql, testPlan.getPlanTitle(),
				testPlan.getPlanDesc(), testPlan.getPlanStatus(),
				testPlan.getPlantype());
		
		return getMaxId();
	}
	public int getMaxId() {

		String sql = "SELECT MAX(planid) FROM plan";

		int maxID = jdbcTemplate.queryForObject(sql, new Object[] {},
				Integer.class);
		if (maxID > 0) {
			return maxID;
		}
		return 0;

	}
	
	public void update(TestPlan testPlan) {
        String sql = "Update plan SET title = ?, description = ?, status = ?, type = ? where planid = ?";
        jdbcTemplate.update(sql, testPlan.getPlanTitle(), testPlan.getPlanDesc(), testPlan.getPlanStatus(),
        		testPlan.getPlantype(), testPlan.getPlanId());
	}


	public void delete(int planid) {
		String sql = "DELETE FROM plan WHERE planid = ?";
	    jdbcTemplate.update(sql, planid);
	}
	
	
	

	@Override
	public boolean isExist(TestPlan testPlan) {

		String sql = "SELECT count(*) FROM plan where title = ?";

		int count = jdbcTemplate.queryForObject(sql,
				new Object[] { testPlan.getPlanTitle() }, Integer.class);

		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<TestPlan> list() {
		System.out.println("in get plan 1");
		String sql = "select * from plan ORDER BY planid DESC limit 4";

		List<TestPlan> listPlan = jdbcTemplate.query(sql,
				new RowMapper<TestPlan>() {

					@Override
					public TestPlan mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						TestPlan testPlan = new TestPlan();
						testPlan.setPlanId(rs.getInt("planid"));
						testPlan.setPlanTitle(rs.getString("title"));
						testPlan.setPlanDesc(rs.getString("description"));
						testPlan.setPlanStatus(rs.getString("status"));
						testPlan.setPlantype(rs.getString("type"));
						
						return testPlan;
					}
				});
		return listPlan;
	}
	@Override
	public List<TestPlan> planById(int planid) {
		String sql = "select * from plan where planid="+planid;

		List<TestPlan> listPlan = jdbcTemplate.query(sql,
				new RowMapper<TestPlan>() {

					@Override
					public TestPlan mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						TestPlan testPlan = new TestPlan();
						testPlan.setPlanId(rs.getInt("planid"));
						testPlan.setPlanTitle(rs.getString("title"));
						testPlan.setPlanDesc(rs.getString("description"));
						testPlan.setPlanStatus(rs.getString("status"));
						testPlan.setPlantype(rs.getString("type"));
						
						return testPlan;
					}
				});
		return listPlan;
	}
	
	
	

}
