package com.rest.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.rest.models.DataSet;
import com.rest.models.TestScript;

public class TestScriptDaoImpl implements TestScriptDao {

	private JdbcTemplate jdbcTemplate;

	public TestScriptDaoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int save(TestScript testScript) {
		synchronized (this) {
			String sql = "insert into testsc (title,description,instruction,caseid) values(?,?,?,?)";
			jdbcTemplate.update(sql, testScript.getScriptTitle(),
					testScript.getScriptDesc(), testScript.getScriptInst(),
					testScript.getCaseId());

			for (int i = 0; i < testScript.getDataSet().size(); i++) {
				String sql1 = "insert into scriptdataset (dataset,output,testscid) values(?,?,?)";
				jdbcTemplate.update(sql1, testScript.getDataSet().get(i)
						.getInput(),
						testScript.getDataSet().get(i).getOutput(), getMaxId());

			}
			return getMaxId();
		}
	}

	public int getMaxId() {

		String sql = "SELECT MAX(testscid) FROM testsc";

		int maxID = jdbcTemplate.queryForObject(sql, new Object[] {},
				Integer.class);
		if (maxID > 0) {
			return maxID;
		}
		return 0;

	}

	public void update(TestScript testScript) {
		String sql = "Update testsc SET title = ?, description = ?, instruction = ?, caseid = ?"
				+ " where testscid = ?";

		jdbcTemplate.update(sql, testScript.getScriptTitle(),
				testScript.getScriptDesc(), testScript.getScriptInst(),
				testScript.getCaseId(), testScript.getScriptId());

		for (int i = 0; i < testScript.getDataSet().size(); i++) {
			if (testScript.getDataSet().get(i).getDataSetId() == 0) {

				String sql3 = "insert into scriptdataset (dataset,output,testscid) values(?,?,?)";

				jdbcTemplate.update(sql3, testScript.getDataSet().get(i)
						.getInput(),
						testScript.getDataSet().get(i).getOutput(), testScript
								.getDataSet().get(i).getScriptId());

			}
			String sql2 = "Update scriptdataset SET dataset = ?,output=? where scriptdatasetid=?";
			jdbcTemplate.update(sql2,
					testScript.getDataSet().get(i).getInput(), testScript
							.getDataSet().get(i).getOutput(), testScript
							.getDataSet().get(i).getDataSetId());

		}
	}

	public void delete(int scriptid) {
		String sql = "DELETE FROM testsc WHERE testscid = ?";
		jdbcTemplate.update(sql, scriptid);
	}

	@Override
	public boolean isExist(TestScript testScript) {

		String sql = "SELECT count(*) FROM testsc where title = ? AND caseid = ?";
		int count = jdbcTemplate.queryForObject(
				sql,
				new Object[] { testScript.getScriptTitle(),
						testScript.getCaseId() }, Integer.class);

		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<TestScript> list() {
		String sql = "select * from testsc ORDER BY testscid DESC";

		List<TestScript> listScript = jdbcTemplate.query(sql,
				new RowMapper<TestScript>() {

					@Override
					public TestScript mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						TestScript testScript = new TestScript();
						testScript.setScriptId(rs.getInt("testscid"));
						testScript.setScriptTitle(rs.getString("title"));
						testScript.setScriptDesc(rs.getString("description"));
						testScript.setScriptInst(rs.getString("instruction"));
						testScript.setCaseId(rs.getInt("caseid"));

						testScript.setDataSet(getdataSet(rs.getInt("testscid")));

						return testScript;
					}
				});
		return listScript;
	}

	@Override
	public List<TestScript> scriptByCaseId(int caseid) {

		String sql = "select * from testsc where caseid=" + caseid;

		List<TestScript> listScript = jdbcTemplate.query(sql,
				new RowMapper<TestScript>() {

					@Override
					public TestScript mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						TestScript testScript = new TestScript();
						testScript.setScriptId(rs.getInt("testscid"));
						testScript.setScriptTitle(rs.getString("title"));
						testScript.setScriptDesc(rs.getString("description"));
						testScript.setScriptInst(rs.getString("instruction"));
						testScript.setCaseId(rs.getInt("caseid"));
						testScript.setDataSet(getdataSet(rs.getInt("testscid")));
						return testScript;
					}
				});
		return listScript;

	}

	@Override
	public List<TestScript> scriptById(int scriptid) {
		final int id = scriptid;
		String sql = "select * from testsc where testscid=" + scriptid;

		List<TestScript> listScript = jdbcTemplate.query(sql,
				new RowMapper<TestScript>() {

					@Override
					public TestScript mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						TestScript testScript = new TestScript();
						testScript.setScriptId(rs.getInt("testscid"));
						testScript.setScriptTitle(rs.getString("title"));
						testScript.setScriptDesc(rs.getString("description"));
						testScript.setScriptInst(rs.getString("instruction"));
						testScript.setCaseId(rs.getInt("caseid"));
						testScript.setDataSet(getdataSet(id));

						return testScript;
					}
				});
		return listScript;

	}

	public List<DataSet> getdataSet(int scriptid) {

		String sql = "select * from scriptdataset where testscid=" + scriptid;

		List<DataSet> listdataSet = jdbcTemplate.query(sql,
				new RowMapper<DataSet>() {

					@Override
					public DataSet mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						DataSet dataSet = new DataSet();
						dataSet.setDataSetId(rs.getInt("scriptdatasetid"));
						dataSet.setInput(rs.getString("dataset"));
						dataSet.setOutput(rs.getString("output"));
						dataSet.setScriptId(rs.getInt("testscid"));

						return dataSet;
					}
				});

		return listdataSet;
	}

	@Override
	public void deleteds(int dsid) {
		String sql = "DELETE FROM scriptdataset WHERE scriptdatasetid = ?";
		jdbcTemplate.update(sql, dsid);

	}

}
