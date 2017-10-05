package com.rest.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.rest.models.TestExecution;
import com.rest.models.TestPlan;
import com.rest.models.TestScript;

public class TestExecutionDaoImpl implements TestExecutionDao {
	
	private JdbcTemplate jdbcTemplate;

	public TestExecutionDaoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int save(TestExecution testExecution) {
		String sql = "insert into testexecution (testPlan,testCase,runon,result,status,testscid) values(?,?,?,?,?,?)";

		jdbcTemplate.update(sql, testExecution.getTestPlan(),testExecution.getTestCase(),testExecution.getRunOn(),
				testExecution.getResult(), testExecution.getScriptStatus(),
				testExecution.getScriptId());
		return getMaxId();
	}
	public int getMaxId() {

		String sql = "SELECT MAX(testexecutionid) FROM testexecution";

		int maxID = jdbcTemplate.queryForObject(sql, new Object[] {},
				Integer.class);
		if (maxID > 0) {
			return maxID;
		}
		return 0;

	}
	


	public void delete(int testexecutionid) {
		String sql = "DELETE FROM testexecution WHERE testexecutionid = ?";
	    jdbcTemplate.update(sql, testexecutionid);
	}

	

	@Override
	public List<TestExecution> list() {
		String sql = "select * from testexecution ORDER BY testexecutionid DESC";

		List<TestExecution> testExecution = jdbcTemplate.query(sql,
				new RowMapper<TestExecution>() {

					@Override
					public TestExecution mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						TestExecution testExecution = new TestExecution();
						testExecution.setExecutionId(rs.getInt("testexecutionid"));
						testExecution.setRunOn(rs.getString("runon"));
						testExecution.setResult(rs.getString("result"));
						testExecution.setScriptStatus(rs.getString("status"));
						testExecution.setScriptId(rs.getInt("testscid"));
						testExecution.setTestPlan(rs.getString("testPlan"));
						testExecution.setTestCase(rs.getString("testCase"));
						return testExecution;
					}
				});
		return testExecution;
	}
	
	@Override
	public List<TestExecution> getExecutionByScriptId(int id) {
	
		String sql = "select * from testexecution where testscid="+id;

		List<TestExecution> testExe = jdbcTemplate.query(sql,
				new RowMapper<TestExecution>() {

					@Override
					public TestExecution mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						TestExecution te = new TestExecution();
						te.setScriptId(rs.getInt("testscid"));
						te.setExecutionId(rs.getInt("testexecutionid"));
						te.setRunOn(rs.getString("runon"));
						te.setResult(rs.getString("result"));
						te.setScriptStatus(rs.getString("status"));
						

						return te;
					}
				});
		return testExe;
		
	}
	


}
