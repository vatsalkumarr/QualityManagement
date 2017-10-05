package com.rest.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.rest.models.TestCase;
import com.rest.models.TestPlan;

public class TestCaseDaoImpl implements TestCaseDao {

	private JdbcTemplate jdbcTemplate;

	public TestCaseDaoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int save(TestCase testCase) {

		synchronized (this) {

			String sql = "insert into testcase (title,description,planid) values(?,?,?)";

			jdbcTemplate.update(sql, testCase.getCaseTitle(),
					testCase.getCaseDesc(), testCase.getPlanId());

			return getMaxId();
		}
	}

	public int getMaxId() {

		String sql = "SELECT MAX(caseid) FROM testcase";

		int maxID = jdbcTemplate.queryForObject(sql, new Object[] {},
				Integer.class);

		if (maxID > 0) {
			return maxID;
		}
		return 0;

	}

	public void update(TestCase testCase) {
		String sql = "Update testcase SET title = ?, description = ?, planid = ? where caseid = ?";

		jdbcTemplate.update(sql, testCase.getCaseTitle(),
				testCase.getCaseDesc(), testCase.getPlanId(),
				testCase.getCaseid());
	}

	public void delete(int caseid) {
		String sql = "DELETE FROM testcase WHERE caseid = ?";
		jdbcTemplate.update(sql, caseid);
	}

	@Override
	public boolean isExist(TestCase testCase) {
		synchronized (this) {

			System.out.println("in case exist");
			String sql = "SELECT count(*) FROM testcase where title = ? AND planid = ?";
			int count = jdbcTemplate.queryForObject(sql, new Object[] {
					testCase.getCaseTitle(), testCase.getPlanId() },
					Integer.class);

			if (count > 0) {
				System.out.println("count: " + count);
				return true;
			}
			return false;

		}
	}

	@Override
	public List<TestCase> list() {
		String sql = "select * from testcase ORDER BY caseid DESC";

		List<TestCase> listCase = jdbcTemplate.query(sql,
				new RowMapper<TestCase>() {

					@Override
					public TestCase mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						TestCase testCase = new TestCase();
						testCase.setCaseid(rs.getInt("caseid"));
						testCase.setCaseTitle(rs.getString("title"));
						testCase.setCaseDesc(rs.getString("description"));
						testCase.setPlanId(rs.getInt("planid"));
						return testCase;
					}
				});
		return listCase;
	}

	public List<TestCase> caseById(int caseId) {
		String sql = "select * from testcase where caseid=" + caseId;

		List<TestCase> listCase = jdbcTemplate.query(sql,
				new RowMapper<TestCase>() {

					@Override
					public TestCase mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						TestCase testCase = new TestCase();
						testCase.setCaseid(rs.getInt("caseid"));
						testCase.setCaseTitle(rs.getString("title"));
						testCase.setCaseDesc(rs.getString("description"));
						testCase.setPlanId(rs.getInt("planid"));
						return testCase;
					}
				});
		return listCase;
	}

	public List<TestCase> caseByPlanId(int planId) {
		String sql = "select * from testcase where planid=" + planId;

		List<TestCase> listCase = jdbcTemplate.query(sql,
				new RowMapper<TestCase>() {

					@Override
					public TestCase mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						TestCase testCase = new TestCase();
						testCase.setCaseid(rs.getInt("caseid"));
						testCase.setCaseTitle(rs.getString("title"));
						testCase.setCaseDesc(rs.getString("description"));
						testCase.setPlanId(rs.getInt("planid"));
						return testCase;
					}
				});
		return listCase;
	}

}
